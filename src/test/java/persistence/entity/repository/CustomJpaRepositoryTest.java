package persistence.entity.repository;

import entity.SampleOneWithValidAnnotation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import persistence.DatabaseTest;
import persistence.context.PersistenceContext;
import persistence.context.PersistenceContextImpl;
import persistence.entity.attribute.EntityAttributes;
import persistence.entity.loader.EntityLoader;
import persistence.entity.loader.SimpleEntityLoader;
import persistence.entity.manager.EntityManagerImpl;
import persistence.entity.persister.SimpleEntityPersister;
import persistence.sql.infra.H2SqlConverter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Nested
@DisplayName("CustomJpaRepository 클래스의")
class CustomJpaRepositoryTest extends DatabaseTest {
    private final EntityAttributes entityAttributes = new EntityAttributes();

    @Nested
    @DisplayName("save 메소드는")
    class save {
        @Nested
        @DisplayName("적절한 인스턴스가 주어지면")
        public class withInstance {
            @Test
            @DisplayName("저장한다.")
            void save() {
                //given
                SampleOneWithValidAnnotation sample =
                        new SampleOneWithValidAnnotation("민준", 29);

                setUpFixtureTable(SampleOneWithValidAnnotation.class, new H2SqlConverter());

                EntityLoader entityLoader = new SimpleEntityLoader(jdbcTemplate);
                SimpleEntityPersister simpleEntityPersister = new SimpleEntityPersister(jdbcTemplate, entityLoader, entityAttributes);
                EntityAttributes entityAttributes = new EntityAttributes();
                PersistenceContext persistenceContext = new PersistenceContextImpl(simpleEntityPersister, entityAttributes);
                EntityManagerImpl entityManager = EntityManagerImpl.of(persistenceContext);

                CustomJpaRepository customJpaRepository = new CustomJpaRepository(entityManager, entityAttributes);

                //when
                //then
                assertThat(customJpaRepository.save(sample).toString()).isEqualTo("SampleOneWithValidAnnotation{id=1, name='민준', age=29}");
            }
        }
    }
}
