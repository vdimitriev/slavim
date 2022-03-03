package mk.vedmak.slavim.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.session.ExpiringSession
import org.springframework.session.web.socket.config.annotation.AbstractSessionWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : AbstractSessionWebSocketMessageBrokerConfigurer<ExpiringSession>() {

    private var relayHost: String = "localhost"
    private var relayPort: Int = 61613

    override fun configureStompEndpoints(registry: StompEndpointRegistry) {
        registry.addEndpoint("/ws").withSockJS()
    }

    override fun configureMessageBroker(registry: MessageBrokerRegistry) {
        registry.enableStompBrokerRelay("/queue/", "/topic/")
            .setUserDestinationBroadcast("/topic/unresolved.user.dest")
            .setUserRegistryBroadcast("/topic/registry.broadcast")
            .setRelayHost(relayHost)
            .setRelayPort(relayPort)

        registry.setApplicationDestinationPrefixes("/chatroom")
    }
}
