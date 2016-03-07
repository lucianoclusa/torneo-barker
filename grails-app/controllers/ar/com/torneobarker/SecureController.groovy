package ar.com.torneobarker

import grails.plugin.springsecurity.annotation.Secured

class SecureController {

	@Secured(['ROLE_ADMIN', 'ROLE_USER'])
	def index() {
		render 'Secure access only'
	}
}
