package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

import org.junit.Test;
import com.amazon.speech.ui.Reprompt;

/**
 * Unit test class for {@link RepromptAssert}
 */
public class RepromptAssertTest {

	@Test
	public void shouldAssertThatGivenReprompt() {
		// Given
		Reprompt reprompt = new Reprompt();

		// When
		RepromptAssert repromptAssert = RepromptAssert.assertThat(reprompt);

		// Then
		assertThat(repromptAssert).isNotNull();
	}

	@Test
	public void shouldFailToAssertThatGivenNullReprompt() {
		// Given
		Reprompt reprompt = null;

		// When
		try {
			RepromptAssert.assertThat(reprompt);
			fail("Was expecting an IllegalArgumentException");
		}
		// Then
		catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Cannot make assertions on null Reprompt");
		}
	}

}
