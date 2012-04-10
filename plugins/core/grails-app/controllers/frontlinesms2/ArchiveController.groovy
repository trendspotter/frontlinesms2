package frontlinesms2

import grails.util.GrailsConfig

class ArchiveController extends MessageController {
	def beforeInterceptor = {
		params.sort = params.sort ?: 'date'
		params.order = params.order ?: 'desc'
		params.max = params.max ?: GrailsConfig.config.grails.views.pagination.max
		params.offset  = params.offset ?: 0
		params.starred = params.starred ? params.starred.toBoolean() : false
		return true
	}
	 
	def index = {
		params.sort = 'date'
		def action = params.messageSection ?: 'inbox'
		println "index: $params"
		redirect(action:action, params:params)
	}
	
	def activityList = {
		def activityInstanceList = Activity.findAllByArchivedAndDeleted(true, false)
		render view:'../message/standard', model:[activityInstanceList: activityInstanceList,
				activityInstanceTotal: activityInstanceList.size(),
				messageSection: "activity"]
	}
	
	def folderList = {
		def folderInstanceList = Folder.findAllByArchivedAndDeleted(true, false)
		render view:'../message/standard', model:[folderInstanceList: folderInstanceList,
				itemInstanceTotal: folderInstanceList.size(),
				messageSection: "folder"]
	}
	
	def getShowModel(messageInstanceList) {
		def model = super.getShowModel(messageInstanceList)
		return model
	}
}
