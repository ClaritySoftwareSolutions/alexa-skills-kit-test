package uk.co.claritysoftware.alexa.skills.kit.test.assertj;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Fail.fail;

import java.util.regex.Pattern;
import org.junit.Before;
import org.junit.Test;
import com.amazon.speech.ui.PlainTextOutputSpeech;

/**
 * Unit test class for {@link PlainTextOutputSpeechAssert}
 */
public class PlainTextOutputSpeechAssertTest {

	private PlainTextOutputSpeechAssert plainTextOutputSpeechAssert;

	@Before
	public void setup() {
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText("The quick brown fox jumped over the lazy dog");
		plainTextOutputSpeechAssert = PlainTextOutputSpeechAssert.assertThat(plainTextOutputSpeech);
	}

	@Test
	public void shouldAssertHasTextGivenMatchingText() {
		// Given
		String expectedText = "The quick brown fox jumped over the lazy dog";
		boolean assertedCorrectly = false;

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedText);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasTextGivenNonMatchingText() {
		// Given
		String expectedText = "non matching text";

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedText);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected PlainTextOutputSpeech to have text of <non matching text> but was <The quick brown fox jumped over the lazy dog>");
		}
	}

	@Test
	public void shouldAssertHasTextGivenNullExpectedTextAndNullTextInOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(null);
		plainTextOutputSpeechAssert = PlainTextOutputSpeechAssert.assertThat(plainTextOutputSpeech);
		String expectedText = null;
		boolean assertedCorrectly = false;

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedText);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasTextGivenNullExpectedText() {
		// Given
		String expectedText = null;

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedText);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected PlainTextOutputSpeech to have text of <null> but was <The quick brown fox jumped over the lazy dog>");
		}
	}

	@Test
	public void shouldFailToAssertHasTextGivenExpectedTextAndNullTextInOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(null);
		plainTextOutputSpeechAssert = PlainTextOutputSpeechAssert.assertThat(plainTextOutputSpeech);
		String expectedText = "non matching text";

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedText);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected PlainTextOutputSpeech to have text of <non matching text> but was <null>");
		}
	}

	@Test
	public void shouldAssertHasTextGivenMatchingPattern() {
		// Given
		Pattern expectedPattern = Pattern.compile("(.+)quick brown fox(.+)");
		boolean assertedCorrectly = false;

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedPattern);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasTextGivenNonMatchingPattern() {
		// Given
		Pattern expectedPattern = Pattern.compile("(.+)non matching text");

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedPattern);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected PlainTextOutputSpeech to match text pattern <(.+)non matching text> but was <The quick brown fox jumped over the lazy dog>");
		}
	}

	@Test
	public void shouldAssertHasTextGivenNullExpectedPatternAndNullTextInOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(null);
		plainTextOutputSpeechAssert = PlainTextOutputSpeechAssert.assertThat(plainTextOutputSpeech);
		Pattern expectedPattern = null;
		boolean assertedCorrectly = false;

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedPattern);
			assertedCorrectly = true;

		} catch (AssertionError e) {
			fail("Was not expecting exception with message " + e.getMessage());
		}
		// Then
		assertThat(assertedCorrectly).isTrue();
	}

	@Test
	public void shouldFailToAssertHasTextGivenNullExpectedPattern() {
		// Given
		Pattern expectedPattern = null;

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedPattern);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected PlainTextOutputSpeech to match text pattern <null> but was <The quick brown fox jumped over the lazy dog>");
		}
	}

	@Test
	public void shouldFailToAssertHasTextGivenExpectedPatternAndNullTextInOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = new PlainTextOutputSpeech();
		plainTextOutputSpeech.setText(null);
		plainTextOutputSpeechAssert = PlainTextOutputSpeechAssert.assertThat(plainTextOutputSpeech);
		Pattern expectedPattern = Pattern.compile("(.+)non matching text");

		// When
		try {
			plainTextOutputSpeechAssert.hasText(expectedPattern);
			fail("Was expecting an AssertionError");
		}
		// Then
		catch (AssertionError e) {
			assertThat(e.getMessage()).isEqualTo("Expected PlainTextOutputSpeech to match text pattern <(.+)non matching text> but was <null>");
		}
	}

	@Test
	public void shouldFailToAssertThatGivenNullPlainTextOutputSpeech() {
		// Given
		PlainTextOutputSpeech plainTextOutputSpeech = null;

		// When
		try {
			plainTextOutputSpeechAssert = PlainTextOutputSpeechAssert.assertThat(plainTextOutputSpeech);
			fail("Was expecting an IllegalArgumentException");
		}
		// Then
		catch (IllegalArgumentException e) {
			assertThat(e.getMessage()).isEqualTo("Cannot make assertions on null PlainTextOutputSpeech");
		}
	}

}
