dataSource {
	pooled = true
	driverClassName = "com.mysql.jdbc.Driver"
	dialect = "org.hibernate.dialect.MySQL5InnoDBDialect"
}
hibernate {
	cache.use_second_level_cache = true
	cache.use_query_cache = false
	//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
	cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
	singleSession = true // configure OSIV singleSession mode
	flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
	development {
		dataSource {
			pooled = true
			jmxExport = true
			driverClassName = "org.h2.Driver"
			dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
			url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
		}
	}
	test {
		dataSource {
			dbCreate = "update"
			url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000;DB_CLOSE_ON_EXIT=FALSE"
		}
	}


	production {
		dataSource {
			dbCreate = "update"

			url = "jdbc:mysql://127.5.133.2:3306/torneobarker?autoReconnect=true"
			username = "adminRfTrpFk"
			password = 	"wFibtfcR5wYR"

			/*
			 *        
			 Root User: adminRfTrpFk
			 Root Password: wFibtfcR5wYR
			 Database Name: torneobarker
			 Connection URL: mysql://$OPENSHIFT_MYSQL_DB_HOST:$OPENSHIFT_MYSQL_DB_PORT/
			 */

		}
	}
}

