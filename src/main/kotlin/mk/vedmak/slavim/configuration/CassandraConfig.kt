package mk.vedmak.slavim.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration
import org.springframework.data.cassandra.core.mapping.BasicCassandraMappingContext
import org.springframework.data.cassandra.core.mapping.CassandraMappingContext


@Configuration
class CassandraConfig() : AbstractCassandraConfiguration() {

    override fun getKeyspaceName(): String {
        return "slavim_chat"
    }

    @Bean
    @Throws(ClassNotFoundException::class)
    override fun cassandraMapping(): CassandraMappingContext {
        return BasicCassandraMappingContext()
    }

}
