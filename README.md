Sikuli-based OCR engine

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

