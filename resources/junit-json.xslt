<?xml version='1.0'?>

<xsl:stylesheet version='1.0' xmlns:xsl='http://www.w3.org/1999/XSL/Transform'>
<xsl:output method='text'/>
<xsl:template match='/testsuite'>
<xsl:for-each select='testcase'>{"record": "test-result","artifact": "<xsl:value-of select='$ARTIFACT'/>","version": "<xsl:value-of select='$VERSION'/>","branch": "<xsl:value-of select='$BRANCH'/>","svn": <xsl:value-of select='$SVN'/>,"@timestamp": "<xsl:value-of select='$TIMESTAMP'/>","job": "<xsl:value-of select='$JOB'/>","hardware": "<xsl:value-of select='$HOST'/>","host": "<xsl:value-of select='$HOST'/>","type": "<xsl:value-of select='$TESTTYPE'/>","project": "<xsl:value-of select='$PROJECT'/>","class": "<xsl:value-of select='@classname'/>","name": "<xsl:value-of select='substring-before(@name, "[0]")'/>","seconds": <xsl:value-of select='@time'/>,"result": "<xsl:if test='failure'>failed</xsl:if><xsl:if test='error'>failed</xsl:if><xsl:if test='skipped'>skipped</xsl:if><xsl:if test='not(failure) and not(error) and not(skipped)'>passed</xsl:if>"}
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>
