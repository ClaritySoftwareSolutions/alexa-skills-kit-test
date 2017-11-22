[![Build Status](https://travis-ci.org/ClaritySoftwareSolutions/alexa-skills-kit-test.svg?branch=master)](https://travis-ci.org/ClaritySoftwareSolutions/alexa-skills-kit-test)

----
# Alexa Skills Kit Test Support Library
### Java library to provide test support for objects created with the Alexa Skills Kit
Library containing a number of Hamcrest matchers and AssertJ assert classes to make assertions on Alexa Skills Kit objects.

### Motivation
The motivation behind this library was that the classes provided by the Alexa Skills Kit do not implement equals and hashcode, and no assertion tools (matchers or similar) are provided.  
Assertions in unit tests typically result in several calls to accessor methods, along with casting. For example, to assert the `PlainTextOutoutSpeech` of a `SpeechletResponse`, one might write:
```java
// code to setup the SpeechletResponse skipped for brevity
PlainTextOutoutSpeech plainTextOutputSpeech = (PlainTextOutoutSpeech) speechletResponse.getOutputSpeech();
String outputSpeechText = plainTextOutputSpeech.getText();
assertThat(outputSpeechText, is("expected text"));
```
This library provides Hamcrest matchers and AssertJ assert classes to make assertions easier and neater to write, and easier to read and reason about. 
The above example could be written using the Hamcrest matcher as follows:
```java
// code to setup the SpeechletResponse skipped for brevity
assertThat(outputSpeechText, hasPlainTextOutputSpeechWithText("expected text"));
```
or like this with AssertJ:
```java
// code to setup the SpeechletResponse skipped for brevity
assertThat(outputSpeechText).hasPlainTextOutputSpeechWithText("expected text");
```

### Hamcrest Matchers
The following Hamcrest matchers are provided:

| Alexa Skills Kit Class       | Matcher                      | Method           |
| ---------------------------- | ---------------------------- | ---------------- |
| PlainTextOutputSpeech        | PlainTextOutputSpeechMatcher | hasText(String)  |
|                              |                              | hasText(Pattern) | 
| SsmlOutputSpeech             | SsmlOutputSpeechMatcher      | hasText(String)  |
|                              |                              | hasText(Pattern) | 
| Reprompt                     | RepromptMatcher              | hasPlainTextOutputSpeechWithText(String)  |
|                              |                              | hasPlainTextOutputSpeechWithText(Pattern) | 
|                              |                              | hasSsmlOutputSpeechWithSsml(Pattern) | 
|                              |                              | hasSsmlOutputSpeechWithSsml(Pattern) | 
| SpeechletResponse            | SpeechletResponseMatcher     | hasPlainTextOutputSpeechWithText(String)  |
|                              |                              | hasPlainTextOutputSpeechWithText(Pattern) | 
|                              |                              | hasSsmlOutputSpeechWithSsml(Pattern) | 
|                              |                              | hasSsmlOutputSpeechWithSsml(Pattern) | 
|                              |                              | isATellResponse() | 
|                              |                              | isAnAskResponse() | 
| SpeechletRequestStreamHandler | SpeechletRequestStreamHandlerMatcher | hasApplicationIds(Set<String>) |

### AssertJ Asserts
The following AssertJ assert classes are provided:

| Alexa Skills Kit Class       | AssertJ Assert Class         | Method           |
| ---------------------------- | ---------------------------- | ---------------- |
| PlainTextOutputSpeech        | PlainTextOutputSpeechAssert  | hasText(String)  |
|                              |                              | hasText(Pattern) | 
| SsmlOutputSpeech             | SsmlOutputSpeechAssert       | hasText(String)  |
|                              |                              | hasText(Pattern) | 
| Reprompt                     | RepromptAssert               | hasPlainTextOutputSpeechWithText(String)  |
|                              |                              | hasPlainTextOutputSpeechWithText(Pattern) | 
|                              |                              | hasSsmlOutputSpeechWithSsml(Pattern) | 
|                              |                              | hasSsmlOutputSpeechWithSsml(Pattern) | 
| SpeechletResponse            | SpeechletResponseAssert      | hasPlainTextOutputSpeechWithText(String)  |
|                              |                              | hasPlainTextOutputSpeechWithText(Pattern) | 
|                              |                              | hasSsmlOutputSpeechWithSsml(Pattern) | 
|                              |                              | hasSsmlOutputSpeechWithSsml(Pattern) | 
|                              |                              | isATellResponse() | 
|                              |                              | isAnAskResponse() | 
| SpeechletRequestStreamHandler | SpeechletRequestStreamHandlerAssert | hasApplicationIds(Set<String>) |

### Usage
The library can be used as a pom dependency:
```xml
<dependency>
	<groupId>uk.co.claritysoftware</groupId>
	<artifactId>alexa-skills-kit-test</artifactId>
	<version>1.0.0</version>
	<scope>test</scope>
</dependency>
```

Usage examples can be found in the test classes `*UsageExampleTest` in the `uk.co.claritysoftware.alexa.skills.kit.test.hamcrest`
and `uk.co.claritysoftware.alexa.skills.kit.test.assertj` packages.

### Change Log
* 1.0.0 Initial release

----
Copyright &copy; 2017 [Clarity Software Solutions Limited](https://claritysoftware.co.uk)

