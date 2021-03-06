package frontlinesms2

class Autoreply extends Activity {
//> CONSTANTS
	static String getShortName() { 'autoreply' }

//> PROPERTIES
	String autoreplyText
	
	static constraints = {
		name(blank:false, maxSize:255, validator:NAME_VALIDATOR(Autoreply))
		autoreplyText(blank:false)
	}
	
	static mapping = {
		keyword cascade: 'all'
		version false
	}

//> SERVICES
	def autoreplyService

//> PROCESS METHODS
	def processKeyword(TextMessage message, Keyword matchedKeyword) {
		this.addToMessages(message)
		autoreplyService.doReply(this, message)
		this.save(failOnError:true)
	}
}

