package frontlinesms2.settings

import frontlinesms2.*

class PageSettingsConnection extends geb.Page {
	static url = 'connection/list'
	static at = {
		title.startsWith('Settings')
	}
	
	static content = {
		btnNewConnection { $('#create-connection-btn a') }
		connectionNames { $('.connection-header').find('h2') }
	}
}
