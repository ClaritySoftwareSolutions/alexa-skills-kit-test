package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static uk.co.claritysoftware.alexa.skills.kit.test.util.ReflectionUtils.getFieldValue;

import java.util.List;
import java.util.Set;
import org.assertj.core.api.AbstractAssert;
import com.amazon.speech.speechlet.SpeechletRequestHandler;
import com.amazon.speech.speechlet.lambda.SpeechletRequestStreamHandler;
import com.amazon.speech.speechlet.verifier.ApplicationIdSpeechletRequestEnvelopeVerifier;

/**
 * Specialisation of Assertj {@link AbstractAssert} for making assertions on {@link SpeechletRequestStreamHandler SpeechletRequestStreamHandlers}
 */
public class SpeechletRequestStreamHandlerAssert extends AbstractAssert<SpeechletRequestStreamHandlerAssert, SpeechletRequestStreamHandler> {

	private SpeechletRequestStreamHandlerAssert(SpeechletRequestStreamHandler actual) {
		super(actual, SpeechletRequestStreamHandlerAssert.class);
	}

	public static SpeechletRequestStreamHandlerAssert assertThat(SpeechletRequestStreamHandler actual) {
		if (actual == null) {
			throw new IllegalArgumentException("Cannot make assertions on null SpeechletRequestStreamHandler");
		}
		return new SpeechletRequestStreamHandlerAssert(actual);
	}

	/**
	 * Assert that the {@link SpeechletRequestStreamHandler} contains all of the specified application ids
	 *
	 * @param expectedApplicationIds the expected application ids
	 * @return this {@link SpeechletRequestStreamHandlerAssert} for further assertion chaining
	 */
	public SpeechletRequestStreamHandlerAssert hasApplicationIds(Set<String> expectedApplicationIds) {
		SpeechletRequestHandler requestHandler = getFieldValue(this.actual, "speechletRequestHandler");

		List<ApplicationIdSpeechletRequestEnvelopeVerifier> requestEnvelopeVerifiers = getFieldValue(requestHandler, "requestEnvelopeVerifiers");
		ApplicationIdSpeechletRequestEnvelopeVerifier requestEnvelopeVerifier = requestEnvelopeVerifiers.get(0);
		Set<String> applicationIds = getFieldValue(requestEnvelopeVerifier, "supportedApplicationIds");

		if (expectedApplicationIds == null || !applicationIds.containsAll(expectedApplicationIds)) {
			failWithMessage("Expected applicationIds to contain <%s> but was <%s>", expectedApplicationIds, applicationIds);
		}

		return this;
	}

}
