package ar.com.torneobarker

import grails.plugin.springsecurity.annotation.Secured

@Secured(['ROLE_ADMIN', 'ROLE_USER'])
class HomeController {

    def index() {
		
		
		render(view: '/home/main', model: [posicionesList: posiciones])
	}
}
