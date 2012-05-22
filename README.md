# Sikuli-based OCR engine

[![Build Status](https://secure.travis-ci.org/sarxos/sikuli-ocr.png?branch=master)](http://travis-ci.org/sarxos/sikuli-ocr)

==========

This toll allows you to capture text from your screen. If you tried to read texts with Sikuli itself, 
you probably found that it uses [tesseract-ocr](http://code.google.com/p/tesseract-ocr/) to recognize
text from images available in screen buffer. Well, this recognition is really poor. I my case I found
that only 10% texts has been recognized properly and I tried to recognize numbers only! After this
adventure I found myself in very bad position since I didn't find any Java-based OCR library. I decided
to write my own. It is not very powerful, but it is sufficient for my needs.

## Limitations

1. Does not work with Java 7 :(
2. Recognize only one-line texts
3. Only predefined fonts can be recognized

## Possible Usage

1. All kind of game bots where you have to read texts / numbers directly from the screen
2. Game assistants - health monitors, potion healers, etc
3. Tests automation
4. And many many more

## Prerequisites

1. JRE 6 (or JDK 6) installed and available in PATH
2. Sikuli X installed and available in PATH

## How To Use It

I will try do describe step-by-step how to make this tool running for you. First at all you have to know
that this tool will not recognize all texts - it is strictly connected with font size, its weight, and
family. So for larger / different fonts you have to create separate glyphs library.

### Create Glyphs Library

Glyphs library is nothing more then XML file together with images representing letters / numbers 
all included in one directory.

Sample glyphs library - it will cover only numbers:

```
numbers
  \_ glyphs.xml
  \_ 1.png
  \_ 2.png
  \_ 3.png
  \_ 4.png
  \_ 5.png
  \_ 6.png
  \_ 7.png
  \_ 8.png
  \_ 9.png
  \_ 0.png
```

File `glyphs.xml` contains mapping between images and corresponding chars. For example file `2.png` is
connected with corresponding `2` char. That means Sikuli OCR should find pattern from `2.png` file and 
represent it as char `2`.

```
<glyphs name="numbers">
	<glyph image="0.png" char="0" />
	<glyph image="1.png" char="1" />
	<glyph image="2.png" char="2" />
	<glyph image="3.png" char="3" />
	<glyph image="4.png" char="4" />
	<glyph image="5.png" char="5" />
	<glyph image="6.png" char="6" />
	<glyph image="7.png" char="7" />
	<glyph image="8.png" char="8" />
	<glyph image="9.png" char="9" />
</glyphs>
```

You can find example glyph libraries in the source code. 

By default glyphs libraries are stored in ```data/glyphs``` directory, but you can change this default location
by setting your own path, e.g.:

```java
OCR.setStoragePath("src/main/resources/glyphs");
``` 

### Write Your Code

In all cases you have to know exact coordinates from where you would like to read the text. Those coordinates
are nothing more then screen units - left top corner is (0, 0) and right bottom corner is (1024, 768) assuming
that your screen resolution is 1024 x 768 px.

Assume you would like to read text from region starting in (200, 300), 50 px high and 100 px wide - something like this:

![Test](https://github.com/sarxos/sikuli-ocr/raw/master/src/main/resources/images/test.png)

```java
OCR ocr = OCR.getSpec("numbers");
String text = ocr.read(new Rectangle(200, 300, 100, 50));
System.out.println("Text is: " + text);
```

On the console output you will get:

```
Text is: TEST 1234
```

## License

Copyright (C) 2012 Bartosz Firyn

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.


