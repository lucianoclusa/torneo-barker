package ar.com.torneobarker.users

class User {

	transient springSecurityService

	long id
	String username
	String password
	boolean enabled = true
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	boolean isPasswordEncoded = false
	
	static transients = ['springSecurityService']

	static constraints = {
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role }
	}

	def beforeInsert() {
        isPasswordEncoded = true
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			isPasswordEncoded = false
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}
}
