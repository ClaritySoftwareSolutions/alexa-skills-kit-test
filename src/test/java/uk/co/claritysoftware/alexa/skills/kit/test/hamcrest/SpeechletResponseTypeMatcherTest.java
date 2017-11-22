package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.SpeechletResponseMatcher.isATellResponse;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.SpeechletResponseMatcher.isAnAskResponse;

import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.ui.Reprompt;

/**
 * Unit test class for {@link SpeechletResponseMatcher.SpeechletResponseTypeMatcher}
 */
public class SpeechletResponseTypeMatcherTest {

	@Test
	public void shouldMatchesSafelyAsAskResponse() {
		// Given
		Description description = new StringDescription();
		SpeechletResponseMatcher.SpeechletResponseTypeMatcher matcher = isAnAskResponse();

		SpeechletResponse askResponse = new SpeechletResponse();
		Reprompt reprompt = new Reprompt();
		askResponse.setReprompt(reprompt);
		askResponse.setShouldEndSession(false);

		// When
		boolean matches = matcher.matchesSafely(askResponse, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldNotMatchesSafelyAsAskResponseGivenResponseWithNoReprompt() {
		// Given
		Description description = new StringDescription();
		SpeechletResponseMatcher.SpeechletResponseTypeMatcher matcher = isAnAskResponse();

		SpeechletResponse invalidAskResponse = new SpeechletResponse();
		invalidAskResponse.setReprompt(null);
		invalidAskResponse.setShouldEndSession(false);

		// When
		boolean matches = matcher.matchesSafely(invalidAskResponse, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("has no Reprompt. This is likely to be a Tell Response"));
	}

	@Test
	public void shouldNotMatchesSafelyAsAskResponseGivenResponseWithSessionEndTrue() {
		// Given
		Description description = new StringDescription();
		SpeechletResponseMatcher.SpeechletResponseTypeMatcher matcher = isAnAskResponse();

		SpeechletResponse invalidAskResponse = new SpeechletResponse();
		Reprompt reprompt = new Reprompt();
		invalidAskResponse.setReprompt(reprompt);
		invalidAskResponse.setShouldEndSession(true);

		// When
		boolean matches = matcher.matchesSafely(invalidAskResponse, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("but ShouldEndSession is true. This is likely to be a Tell Response"));
	}

	@Test
	public void shouldMatchesSafelyAsTellResponse() {
		// Given
		Description description = new StringDescription();
		SpeechletResponseMatcher.SpeechletResponseTypeMatcher matcher = isATellResponse();

		SpeechletResponse tellResponse = new SpeechletResponse();
		tellResponse.setReprompt(null);
		tellResponse.setShouldEndSession(true);

		// When
		boolean matches = matcher.matchesSafely(tellResponse, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldNotMatchesSafelyAsTellResponseGivenResponseWithAReprompt() {
		// Given
		Description description = new StringDescription();
		SpeechletResponseMatcher.SpeechletResponseTypeMatcher matcher = isATellResponse();

		SpeechletResponse invalidAskResponse = new SpeechletResponse();
		Reprompt reprompt = new Reprompt();
		invalidAskResponse.setReprompt(reprompt);
		invalidAskResponse.setShouldEndSession(true);

		// When
		boolean matches = matcher.matchesSafely(invalidAskResponse, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("has a Reprompt. This is likely to be an Ask Response"));
	}

	@Test
	public void shouldNotMatchesSafelyAsTellResponseGivenResponseWithSessionEndFalse() {
		// Given
		Description description = new StringDescription();
		SpeechletResponseMatcher.SpeechletResponseTypeMatcher matcher = isATellResponse();

		SpeechletResponse invalidAskResponse = new SpeechletResponse();
		invalidAskResponse.setReprompt(null);
		invalidAskResponse.setShouldEndSession(false);

		// When
		boolean matches = matcher.matchesSafely(invalidAskResponse, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("but ShouldEndSession is false. This is likely to be an Ask Response"));
	}

	@Test
	public void shouldDescribeToForTellResponse() {
		// Given
		Description description = new StringDescription();
		SpeechletResponseMatcher.SpeechletResponseTypeMatcher matcher = isATellResponse();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("Expected SpeechletResponse to be a tell response with no Reprompt and ShouldEndSession set to true"));
	}

	@Test
	public void shouldDescribeToForAskResponse() {
		// Given
		Description description = new StringDescription();
		SpeechletResponseMatcher.SpeechletResponseTypeMatcher matcher = isAnAskResponse();

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("Expected SpeechletResponse to be an ask response with a Reprompt and ShouldEndSession set to false"));
	}

}
