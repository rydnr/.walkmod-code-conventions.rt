package org.acmsl.walkmod;

import java.io.File;
import java.nio.charset.Charset;

import org.acmsl.commons.utils.io.FileUtils;

import org.walkmod.javalang.ASTManager;
import org.walkmod.javalang.ast.CompilationUnit;

import org.apache.ivy.util.FileUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.walkmod.walkers.AbstractWalker;
import org.walkmod.walkers.VisitorContext;

import org.junit.Rule;
import org.junit.rules.TemporaryFolder;

import org.checkthread.annotations.ThreadSafe;

import org.jetbrains.annotations.NotNull;

/**
 * Tests {@link AcmslWriter} indenter.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
 * @since 3.0
 */
@ThreadSafe
@RunWith(JUnit4.class)
public class AcmslWriterTest
{
    /**
     * A temporary folder.
     */
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    
    /**
     * Tests the writer replaces tabs with spaces.
     */
    @Test
    public void no_tabs()
        throws Exception
    {
        @NotNull final AcmslWriter writer = new AcmslWriter();
        @NotNull final VisitorContext context = new VisitorContext();
        @NotNull final File output = folder.newFile("Foo.java");

        context.put(AbstractWalker.ORIGINAL_FILE_KEY, output);

        @NotNull final FileUtils fileUtils = FileUtils.getInstance();
        
        fileUtils.writeFile(output, "\t public class Foo {\n\tint a = 0;\n int b = 0;}", Charset.defaultCharset());

        @NotNull final CompilationUnit unit = ASTManager.parse(output);

        writer.visit(unit, context);

        @NotNull final String code = fileUtils.readFile(output, Charset.defaultCharset());
       
        Assert.assertFalse("Code contains tabs!", code.contains("\t"));
    }
}
