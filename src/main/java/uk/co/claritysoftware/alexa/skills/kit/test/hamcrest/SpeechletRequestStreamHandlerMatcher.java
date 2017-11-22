package uk.co.claritysoftware.alexa.skills.kit.test.hamcrest;

import static java.lang.String.format;
import static uk.co.claritysoftware.alexa.skills.kit.test.util.ReflectionUtils.getFieldValue;

import java.util.List;
import java.util.Set;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import com.amazon.speech.speechlet.SpeechletRequestHandler;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.amazon.speech.speechlet.verifier.ApplicationIdSpeechletRequestEnvelopeVerifier;

/**
 * Hamcrest matchers for making assertions on a {@link SpeechletRequestStreamHandler}
 */
public class SpeechletRequestStreamHandlerMatcher {

	private SpeechletRequestStreamHandlerMatcher() {

	}

	/**
	 * Returns a {@link SpeechletRequestStreamHandlerApplicationIdsMatcher} to assert that the {@link SpeechletRequestStreamHandler}
	 * contains all of the specified application ids
	 *
	 * @param expectedApplicationIds the expected application ids
	 * @return the SpeechletRequestStreamHandlerApplicationIdsMatcher
	 */
	public static SpeechletRequestStreamHandlerApplicationIdsMatcher hasApplicationIds(Set<String> expectedApplicationIds) {
		return new SpeechletRequestStreamHandlerApplicationIdsMatcher(expectedApplicationIds);
	}

	/**
	 * Hamcrest matcher for asserting {@link SpeechletRequestStreamHandler} contains all of the specified application ids
	 */
	static class SpeechletRequestStreamHandlerApplicationIdsMatcher extends TypeSafeDiagnosingMatcher<SpeechletRequestStreamHandler> {

		private final Set<String> expectedApplicationIds;

		private SpeechletRequestStreamHandlerApplicationIdsMatcher(Set<String> expectedApplicationIds) {
			this.expectedApplicationIds = expectedApplicationIds;
		}

		@Override
		protected boolean matchesSafely(SpeechletRequestStreamHandler speechletRequestStreamHandler, Description description) {
			SpeechletRequestHandler requestHandler = getFieldValue(speechletRequestStreamHandler, "speechletRequestHandler");

			List<ApplicationIdSpeechletRequestEnvelopeVerifier> requestEnvelopeVerifiers = getFieldValue(requestHandler, "requestEnvelopeVerifiers");
			ApplicationIdSpeechletRequestEnvelopeVerifier requestEnvelopeVerifier = requestEnvelopeVerifiers.get(0);
			Set<String> applicationIds = getFieldValue(requestEnvelopeVerifier, "supportedApplicationIds");

			if (expectedApplicationIds == null || !applicationIds.containsAll(expectedApplicationIds)) {
				description.appendText(format("was <%s>", applicationIds));
				return false;
			}

			return true;
		}

		@Override
		public void describeTo(Description description) {
			description.appendText(format("Expected SpeechletRequestStreamHandler to have application ids <%s>", expectedApplicationIds));
		}

	}
}
