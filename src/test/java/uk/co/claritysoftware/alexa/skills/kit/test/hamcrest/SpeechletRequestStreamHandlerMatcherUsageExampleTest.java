package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static uk.co.claritysoftware.alexa.skills.kit.test.hamcrest.SpeechletRequestStreamHandlerMatcher.hasApplicationIds;

import java.util.Set;
import org.junit.Test;
import org.mockito.internal.util.collections.Sets;
import com.amazon.speech.speechlet.SpeechletV2;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;

/**
 * Usage examples for {@link SpeechletRequestStreamHandlerMatcher}
 */
public class SpeechletRequestStreamHandlerMatcherUsageExampleTest {

	private static final Set<String> APPLICATION_IDS = Sets.newSet("1234-5678-90ab-cdef");

	private static final SpeechletRequestStreamHandler SPEECHLET_REQUEST_STREAM_HANDLER = new TestSpeechletRequestStreamHandler(mock(SpeechletV2.class), APPLICATION_IDS);

	@Test
	public void shouldAssertHasApplicationIds() {
		Set<String> expectedApplicationIds = APPLICATION_IDS;

		assertThat(SPEECHLET_REQUEST_STREAM_HANDLER, hasApplicationIds(expectedApplicationIds));
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
