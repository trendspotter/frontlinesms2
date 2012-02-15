package frontlinesms2.message

import frontlinesms2.*

@Mixin(frontlinesms2.utils.GebUtil)
class MessageAddContactSpec extends MessageBaseSpec {

	def 'if source of message does not exists in the database then number is displayed'() {
		setup:
			createTestMessages()
			def contactlessMessage = Fmessage.findBySrc('+254778899')
		when:
			go "message/inbox/show/${contactlessMessage.id}"
		then:
			!Contact.findByPrimaryMobile(contactlessMessage.src)
	}
	
	def 'add contact button is displayed and redirects to create contacts page with number field prepopulated'() {
		setup:
			createTestMessages()
			def message = Fmessage.findBySrc('+254778899')
		when:
			go "message/inbox/show/${message.id}"
			$('#add-contact').click()
		then:
			waitFor(10) {$('title').text() == 'Contacts'}
			$('#details').primaryMobile == "+254778899"
	}
}
