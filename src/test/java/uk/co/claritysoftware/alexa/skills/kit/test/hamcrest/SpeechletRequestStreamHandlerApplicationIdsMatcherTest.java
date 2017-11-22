package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static java.util.Collections.emptySet;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.SpeechletRequestStreamHandlerMatcher.hasApplicationIds;

import java.util.Set;
import org.hamcrest.Description;
import org.hamcrest.StringDescription;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * Unit test class for {@link SpeechletRequestStreamHandlerMatcher.SpeechletRequestStreamHandlerApplicationIdsMatcher}
 */
public class SpeechletRequestStreamHandlerApplicationIdsMatcherTest {

	private static final SpeechletV2 SPEECHLET = mock(SpeechletV2.class);

	private static final Set<String> APPLICATION_IDS = Sets.newSet("1234-5678-90ab-cdef", "fedc-ba09-8765-4321");

	private static final SpeechletRequestStreamHandler SPEECHLET_REQUEST_STREAM_HANDLER = new TestSpeechletRequestStreamHandler(SPEECHLET, APPLICATION_IDS);

	@Test
	public void shouldMatchesSafelyGivenExactMatchingSet() {
		// Given
		Set<String> expectedApplicationIds = APPLICATION_IDS;

		Description description = new StringDescription();
		SpeechletRequestStreamHandlerMatcher.SpeechletRequestStreamHandlerApplicationIdsMatcher matcher = hasApplicationIds(expectedApplicationIds);

		// When
		boolean matches = matcher.matchesSafely(SPEECHLET_REQUEST_STREAM_HANDLER, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldMatchesSafelyGivenSubset() {
		// Given
		Set<String> expectedApplicationIds = Sets.newSet("fedc-ba09-8765-4321");

		Description description = new StringDescription();
		SpeechletRequestStreamHandlerMatcher.SpeechletRequestStreamHandlerApplicationIdsMatcher matcher = hasApplicationIds(expectedApplicationIds);

		// When
		boolean matches = matcher.matchesSafely(SPEECHLET_REQUEST_STREAM_HANDLER, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldMatchesSafelyGivenEmptySet() {
		// Given
		Set<String> expectedApplicationIds = emptySet();

		Description description = new StringDescription();
		SpeechletRequestStreamHandlerMatcher.SpeechletRequestStreamHandlerApplicationIdsMatcher matcher = hasApplicationIds(expectedApplicationIds);

		// When
		boolean matches = matcher.matchesSafely(SPEECHLET_REQUEST_STREAM_HANDLER, description);

		// Then
		assertThat(matches, is(true));
		assertThat(description.toString(), is(""));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNonMatchingSet() {
		// Given
		Set<String> expectedApplicationIds = Sets.newSet("1212-3434-abab-cdcd");

		Description description = new StringDescription();
		SpeechletRequestStreamHandlerMatcher.SpeechletRequestStreamHandlerApplicationIdsMatcher matcher = hasApplicationIds(expectedApplicationIds);

		// When
		boolean matches = matcher.matchesSafely(SPEECHLET_REQUEST_STREAM_HANDLER, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <[fedc-ba09-8765-4321, 1234-5678-90ab-cdef]>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenPartialMatchingSet() {
		// Given
		Set<String> expectedApplicationIds = Sets.newSet("fedc-ba09-8765-4321", "1212-3434-abab-cdcd");

		Description description = new StringDescription();
		SpeechletRequestStreamHandlerMatcher.SpeechletRequestStreamHandlerApplicationIdsMatcher matcher = hasApplicationIds(expectedApplicationIds);

		// When
		boolean matches = matcher.matchesSafely(SPEECHLET_REQUEST_STREAM_HANDLER, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <[fedc-ba09-8765-4321, 1234-5678-90ab-cdef]>"));
	}

	@Test
	public void shouldNotMatchesSafelyGivenNull() {
		// Given
		Set<String> expectedApplicationIds = null;

		Description description = new StringDescription();
		SpeechletRequestStreamHandlerMatcher.SpeechletRequestStreamHandlerApplicationIdsMatcher matcher = hasApplicationIds(expectedApplicationIds);

		// When
		boolean matches = matcher.matchesSafely(SPEECHLET_REQUEST_STREAM_HANDLER, description);

		// Then
		assertThat(matches, is(false));
		assertThat(description.toString(), is("was <[fedc-ba09-8765-4321, 1234-5678-90ab-cdef]>"));
	}

	@Test
	public void shouldDescribeTo() {
		// Given
		Description description = new StringDescription();
		SpeechletRequestStreamHandlerMatcher.SpeechletRequestStreamHandlerApplicationIdsMatcher matcher = hasApplicationIds(APPLICATION_IDS);

		// When
		matcher.describeTo(description);

		// Then
		assertThat(description.toString(), is("Expected SpeechletRequestStreamHandler to have application ids <[1234-5678-90ab-cdef, fedc-ba09-8765-4321]>"));
	}

	/**
	 * Simple implementation of {@link SpeechletRequestStreamHandler} for test purposes
	 */
	private static class TestSpeechletRequestStreamHandler extends SpeechletRequestStreamHandler {

		public TestSpeechletRequestStreamHandler(SpeechletV2 speechlet, Set<String> supportedApplicationIds) {
			super(speechlet, supportedApplicationIds);
		}
	}
}
