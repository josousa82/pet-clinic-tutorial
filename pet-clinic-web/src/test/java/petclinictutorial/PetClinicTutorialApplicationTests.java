package petclinictutorial;

import com.springframework.petclinictutorial.PetClinicTutorialApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={PetClinicTutorialApplication.class})
class PetClinicTutorialApplicationTests {
        @BeforeEach
        void setUp() {
        }

        @Test
        void name() {

        }
    }
