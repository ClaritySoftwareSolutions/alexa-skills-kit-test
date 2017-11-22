package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;
import com.amazon.speech.ui.SsmlOutputSpeech;

/**
 * Unit test class for {@link SsmlOutputSpeechAssert}
 */
public class SsmlOutputSpeechAssertTest {

	private SsmlOutputSpeechAssert ssmlOutputSpeechAssert;

	@Before
	public void setup() {
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml("The quick brown fox <break time=\"1s\"/> jumped over the lazy dog");
		ssmlOutputSpeechAssert = SsmlOutputSpeechAssert.assertThat(ssmlOutputSpeech);
	}

	@Test
	public void shouldAssertHasSsmlGivenMatchingSsml() {
		// Given
		String expectedSsml = "The quick brown fox <break time=\"1s\"/> jumped over the lazy dog";
		boolean assertedCorrectly = false;

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedSsml);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasSsmlGivenNonMatchingSsml() {
		// Given
		String expectedSsml = "non matching ssml";

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedSsml);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected SsmlOutputSpeech to have ssml of <non matching ssml> but was <The quick brown fox <break time=\"1s\"/> jumped over the lazy dog>");
		}
	}

	@Test
	public void shouldAssertHasSsmlGivenNullExpectedTextAndNullTextInOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml(null);
		ssmlOutputSpeechAssert = SsmlOutputSpeechAssert.assertThat(ssmlOutputSpeech);
		String expectedText = null;
		boolean assertedCorrectly = false;

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedText);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasSsmlGivenNullExpectedText() {
		// Given
		String expectedText = null;

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedText);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected SsmlOutputSpeech to have ssml of <null> but was <The quick brown fox <break time=\"1s\"/> jumped over the lazy dog>");
		}
	}

	@Test
	public void shouldFailToAssertHasSsmlGivenExpectedTextAndNullTextInOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml(null);
		ssmlOutputSpeechAssert = SsmlOutputSpeechAssert.assertThat(ssmlOutputSpeech);
		String expectedText = "non matching text";

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedText);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected SsmlOutputSpeech to have ssml of <non matching text> but was <null>");
		}
	}

	@Test
	public void shouldAssertHasSsmlGivenMatchingPattern() {
		// Given
		Pattern expectedPattern = Pattern.compile("(.+)quick brown fox <break time=\"[0-9]s\"/>(.+)");
		boolean assertedCorrectly = false;

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedPattern);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasSsmlGivenNonMatchingPattern() {
		// Given
		Pattern expectedPattern = Pattern.compile("(.+)non matching text");

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedPattern);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected SsmlOutputSpeech to match ssml pattern <(.+)non matching text> but was <The quick brown fox <break time=\"1s\"/> jumped over the lazy dog>");
		}
	}

	@Test
	public void shouldAssertHasSsmlGivenNullExpectedPatternAndNullTextInOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml(null);
		ssmlOutputSpeechAssert = SsmlOutputSpeechAssert.assertThat(ssmlOutputSpeech);
		Pattern expectedPattern = null;
		boolean assertedCorrectly = false;

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedPattern);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasSsmlGivenNullExpectedPattern() {
		// Given
		Pattern expectedPattern = null;

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedPattern);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected SsmlOutputSpeech to match ssml pattern <null> but was <The quick brown fox <break time=\"1s\"/> jumped over the lazy dog>");
		}
	}

	@Test
	public void shouldFailToAssertHasSsmlGivenExpectedPatternAndNullTextInOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = new SsmlOutputSpeech();
		ssmlOutputSpeech.setSsml(null);
		ssmlOutputSpeechAssert = SsmlOutputSpeechAssert.assertThat(ssmlOutputSpeech);
		Pattern expectedPattern = Pattern.compile("(.+)non matching text");

		// When
		try {
			ssmlOutputSpeechAssert.hasSsml(expectedPattern);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected SsmlOutputSpeech to match ssml pattern <(.+)non matching text> but was <null>");
		}
	}

	@Test
	public void shouldFailToAssertThatGivenNullSsmlOutputSpeech() {
		// Given
		SsmlOutputSpeech ssmlOutputSpeech = null;

		// When
		try {
			ssmlOutputSpeechAssert = SsmlOutputSpeechAssert.assertThat(ssmlOutputSpeech);
			fail("Was expecting an IllegalArgumentException");
		}
		// Then
		catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Cannot make assertions on null SsmlOutputSpeech");
		}
	}

}
