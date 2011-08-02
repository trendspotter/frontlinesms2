<div id="message-list">
	<g:if test="${messageInstanceTotal > 0}">
		<g:hiddenField name="checkedMessageIdList" value=""/>
		<g:hiddenField name="messageSection" value="${messageSection}"/>
		<g:hiddenField name="ownerId" value="${ownerInstance?.id}"/>
		<table id="messages">
			<thead>
				<tr>
					<td><g:checkBox name="message" value="0" disabled="${messageSection == 'trash' ? 'true': 'false'}" checked="false" onclick="checkAllMessages()"/></td>
					<td></td>
					<g:if test="${messageSection == 'sent' || messageSection == 'pending'}">
				    	<td><g:message code="fmessage.src.label" default="To"/></td>
				    </g:if>
				    <g:else>
				    	<td><g:message code="fmessage.src.label" default="From"/></td>
				    </g:else>
				    <td><g:message code="fmessage.text.label" default="Message"/></td>
				    <td><g:message code="fmessage.date.label" default="Date"/></td>
				</tr>
			</thead>
			<tbody>
				<g:each in="${messageInstanceList}" status="i" var="m">
					<tr class="${m == messageInstance?'selected':''} ${m.read?'read':'unread'} ${m.status}" id="message-${m.id}">
						<td><g:checkBox name="message" checked="${params.checkedId == m.id+'' ? 'true': 'false'}" value="${m.id}" onclick="updateMessageDetails(${m.id});" disabled="${messageSection == 'trash' ? 'true': 'false'}"/></td>
						<td id="star_column">
						  <g:remoteLink controller="message" action="changeStarStatus" params='[messageId: "${m.id}"]' onSuccess="setStarStatus('star-${m.id}',data)">
						  <div id="star-${m.id}" class="${m.starred? 'starred':'unstarred'}">
					
						  </div>
						  </g:remoteLink>
						</td>
						<td>
								<g:link action="${messageSection}" params="${params + [messageId: m.id]}">
									${m.displaySrc}
								</g:link>
						</td>
						<td>
								<g:link action="${messageSection}" params="${params + [messageId: m.id]}">
								  ${m.displayText}
								</g:link>
						</td>
						<td>
								<g:link  action="${messageSection}" params="${params + [messageId: m.id]}">
									<g:formatDate format="dd-MMM-yyyy hh:mm" date="${m.dateCreated}" />
								</g:link>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</g:if>
	<g:else>
		<div id="messages">
			No messages
		</div>
	</g:else>
</div>
