package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

import org.junit.Test;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Reprompt;

/**
 * Unit test class for {@link SpeechletResponseAssert}
 */
public class SpeechletResponseAssertTest {

	@Test
	public void shouldAssertThatGivenSpeechletResponse() {
		// Given
		SpeechletResponse speechletResponse = new SpeechletResponse();

		// When
		SpeechletResponseAssert speechletResponseAssert = SpeechletResponseAssert.assertThat(speechletResponse);

		// Then
		assertThat(speechletResponseAssert).isNotNull();
	}

	@Test
	public void shouldFailToAssertThatGivenNullSpeechletResponse() {
		// Given
		SpeechletResponse speechletResponse = null;

		// When
		try {
			SpeechletResponseAssert.assertThat(speechletResponse);
			fail("Was expecting an IllegalArgumentException");
		}
		// Then
		catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Cannot make assertions on null SpeechletResponse");
		}
	}

	@Test
	public void shouldAssertIsAnAskResponseGivenRepromptAndEndSessionFalse() {
		// Given
		SpeechletResponse speechletResponse = new SpeechletResponse();
		Reprompt reprompt = new Reprompt();
		speechletResponse.setReprompt(reprompt);
		speechletResponse.setShouldEndSession(false);

		SpeechletResponseAssert speechletResponseAssert = SpeechletResponseAssert.assertThat(speechletResponse);

		boolean assertedCorrectly = false;

		// When
		try {
			speechletResponseAssert.isAnAskResponse();
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertIsAnAskResponseGivenRepromptAndEndSessionTrue() {
		// Given
		SpeechletResponse speechletResponse = new SpeechletResponse();
		Reprompt reprompt = new Reprompt();
		speechletResponse.setReprompt(reprompt);
		speechletResponse.setShouldEndSession(true);

		SpeechletResponseAssert speechletResponseAssert = SpeechletResponseAssert.assertThat(speechletResponse);

		// When
		try {
			speechletResponseAssert.isAnAskResponse();
			fail("Was expecting an AssertionError");

		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Was expecting the session to be set to not end, but it was. This is likely to be a Tell Response");
		}
	}

	@Test
	public void shouldFailToAssertIsAnAskResponseGivenNullRepromptAndEndSessionFalse() {
		// Given
		SpeechletResponse speechletResponse = new SpeechletResponse();
		Reprompt reprompt = null;
		speechletResponse.setReprompt(reprompt);
		speechletResponse.setShouldEndSession(false);

		SpeechletResponseAssert speechletResponseAssert = SpeechletResponseAssert.assertThat(speechletResponse);

		// When
		try {
			speechletResponseAssert.isAnAskResponse();
			fail("Was expecting an AssertionError");

		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Was expecting a Reprompt object, but there was none. This is likely to be a Tell Response");
		}
	}

	@Test
	public void shouldAssertIsATellResponseGivenNullRepromptAndEndSessionTrue() {
		// Given
		SpeechletResponse speechletResponse = new SpeechletResponse();
		Reprompt reprompt = null;
		speechletResponse.setReprompt(reprompt);
		speechletResponse.setShouldEndSession(true);

		SpeechletResponseAssert speechletResponseAssert = SpeechletResponseAssert.assertThat(speechletResponse);

		boolean assertedCorrectly = false;

		// When
		try {
			speechletResponseAssert.isATellResponse();
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertIsATellResponseGivenRepromptAndEndSessionTrue() {
		// Given
		SpeechletResponse speechletResponse = new SpeechletResponse();
		Reprompt reprompt = new Reprompt();
		speechletResponse.setReprompt(reprompt);
		speechletResponse.setShouldEndSession(true);

		SpeechletResponseAssert speechletResponseAssert = SpeechletResponseAssert.assertThat(speechletResponse);

		// When
		try {
			speechletResponseAssert.isATellResponse();
			fail("Was expecting an AssertionError");

		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Was expecting not to have a Reprompt object, but there was one. This is likely to be an Ask Response");
		}
	}

	@Test
	public void shouldFailToAssertIsATellResponseGivenNullRepromptAndEndSessionFalse() {
		// Given
		SpeechletResponse speechletResponse = new SpeechletResponse();
		Reprompt reprompt = null;
		speechletResponse.setReprompt(reprompt);
		speechletResponse.setShouldEndSession(false);

		SpeechletResponseAssert speechletResponseAssert = SpeechletResponseAssert.assertThat(speechletResponse);

		// When
		try {
			speechletResponseAssert.isATellResponse();
			fail("Was expecting an AssertionError");

		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Was expecting the session to be set to end, but it wasn't. This is likely to be an Ask Response");
		}
	}
}
