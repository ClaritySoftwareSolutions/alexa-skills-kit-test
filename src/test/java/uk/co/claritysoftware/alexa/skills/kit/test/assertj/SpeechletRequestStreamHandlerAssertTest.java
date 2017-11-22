package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;
import static org.mockito.Mockito.mock;

import java.util.Set;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * Unit test class for {@link SpeechletRequestStreamHandlerAssert}
 */
public class SpeechletRequestStreamHandlerAssertTest {

	private static final SpeechletV2 SPEECHLET = mock(SpeechletV2.class);

	private static final Set<String> APPLICATION_IDS = Sets.newSet("1234-5678-90ab-cdef", "fedc-ba09-8765-4321");

	private static final SpeechletRequestStreamHandler SPEECHLET_REQUEST_STREAM_HANDLER = new TestSpeechletRequestStreamHandler(SPEECHLET, APPLICATION_IDS);

	@Test
	public void shouldAssertThatGivenSpeechletRequestStreamHandler() {
		// Given

		// When
		SpeechletRequestStreamHandlerAssert speechletRequestStreamHandlerAssert = SpeechletRequestStreamHandlerAssert.assertThat(SPEECHLET_REQUEST_STREAM_HANDLER);

		// Then
		assertThat(speechletRequestStreamHandlerAssert).isNotNull();
	}

	@Test
	public void shouldFailToAssertThatGivenNullSpeechletRequestStreamHandler() {
		// Given
		SpeechletRequestStreamHandler speechletRequestStreamHandler = null;

		// When
		try {
			SpeechletRequestStreamHandlerAssert.assertThat(speechletRequestStreamHandler);
			fail("Was expecting an IllegalArgumentException");
		}
		// Then
		catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Cannot make assertions on null SpeechletRequestStreamHandler");
		}
	}

	@Test
	public void shouldAssertHasApplicationIdsGivenExactMatchingSet() {
		// Given
		SpeechletRequestStreamHandlerAssert speechletRequestStreamHandlerAssert = SpeechletRequestStreamHandlerAssert.assertThat(SPEECHLET_REQUEST_STREAM_HANDLER);

		Set<String> expectedApplicationIds = Sets.newSet("1234-5678-90ab-cdef", "fedc-ba09-8765-4321");

		boolean assertedCorrectly = false;

		// When
		try {
			speechletRequestStreamHandlerAssert.hasApplicationIds(expectedApplicationIds);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldAssertHasApplicationIdsGivenSubset() {
		// Given
		SpeechletRequestStreamHandlerAssert speechletRequestStreamHandlerAssert = SpeechletRequestStreamHandlerAssert.assertThat(SPEECHLET_REQUEST_STREAM_HANDLER);

		Set<String> expectedApplicationIds = Sets.newSet("fedc-ba09-8765-4321");

		boolean assertedCorrectly = false;

		// When
		try {
			speechletRequestStreamHandlerAssert.hasApplicationIds(expectedApplicationIds);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldAssertHasApplicationIdsGivenEmptySet() {
		// Given
		SpeechletRequestStreamHandlerAssert speechletRequestStreamHandlerAssert = SpeechletRequestStreamHandlerAssert.assertThat(SPEECHLET_REQUEST_STREAM_HANDLER);

		Set<String> expectedApplicationIds = emptySet();
		boolean assertedCorrectly = false;

		// When
		try {
			speechletRequestStreamHandlerAssert.hasApplicationIds(expectedApplicationIds);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasApplicationIdsGivenNonMatchingSet() {
		// Given
		SpeechletRequestStreamHandlerAssert speechletRequestStreamHandlerAssert = SpeechletRequestStreamHandlerAssert.assertThat(SPEECHLET_REQUEST_STREAM_HANDLER);

		Set<String> expectedApplicationIds = Sets.newSet("1212-3434-abab-cdcd");

		// When
		try {
			speechletRequestStreamHandlerAssert.hasApplicationIds(expectedApplicationIds);
			fail("Was expecting an AssertionException");

		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected applicationIds to contain <[1212-3434-abab-cdcd]> but was <[fedc-ba09-8765-4321, 1234-5678-90ab-cdef]>");
		}
	}

	@Test
	public void shouldFailToAssertHasApplicationIdsGivenPartialMatchingSet() {
		// Given
		SpeechletRequestStreamHandlerAssert speechletRequestStreamHandlerAssert = SpeechletRequestStreamHandlerAssert.assertThat(SPEECHLET_REQUEST_STREAM_HANDLER);

		Set<String> expectedApplicationIds = Sets.newSet("fedc-ba09-8765-4321", "1212-3434-abab-cdcd");

		// When
		try {
			speechletRequestStreamHandlerAssert.hasApplicationIds(expectedApplicationIds);
			fail("Was expecting an AssertionException");

		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected applicationIds to contain <[fedc-ba09-8765-4321, 1212-3434-abab-cdcd]> but was <[fedc-ba09-8765-4321, 1234-5678-90ab-cdef]>");
		}
	}

	@Test
	public void shouldFailToAssertHasApplicationIdsGivenNull() {
		// Given
		SpeechletRequestStreamHandlerAssert speechletRequestStreamHandlerAssert = SpeechletRequestStreamHandlerAssert.assertThat(SPEECHLET_REQUEST_STREAM_HANDLER);

		Set<String> expectedApplicationIds = null;

		// When
		try {
			speechletRequestStreamHandlerAssert.hasApplicationIds(expectedApplicationIds);
			fail("Was expecting an AssertionException");

		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected applicationIds to contain <null> but was <[fedc-ba09-8765-4321, 1234-5678-90ab-cdef]>");
		}
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
