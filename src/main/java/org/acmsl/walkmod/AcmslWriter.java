package org.acmsl.walkmod;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.walkmod.exceptions.WalkModException;
import org.walkmod.javalang.ast.BlockComment;
import org.walkmod.javalang.ast.Comment;
import org.walkmod.javalang.ast.CompilationUnit;
import org.walkmod.javalang.ast.Node;
import org.walkmod.javalang.visitors.VoidVisitorAdapter;
import org.walkmod.walkers.VisitorContext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.jetbrains.annotations.NotNull;
import org.checkthread.annotations.ThreadSafe;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.TypeSpec;

/**
 * Walkmod-based Java code indenter based on ACM S.L. conventions.
 * @author <a href="mailto:chous@acm-sl.org">Jose San Leandro</a>
 * @since 3.0
 */
@ThreadSafe
public class AcmslWriter
    extends VoidVisitorAdapter<VisitorContext>
{
    /**
     * Visitor implementation for each {@link CompilationUnit}.
     * @param unit the unit.
     * @param context the {@link VisitorContext context}.
     */
    @Override
    public void visit(@NotNull final CompilationUnit unit, @NotNull final VisitorContext context)
    {
        @NotNull final Log log = LogFactory.getLog(AcmslWriter.class);

        @NotNull final JavaFile source =
            JavaFile.builder("com.foo.bar", TypeSpec.classBuilder("Foo").build()).build();

        log.info(source.toString());
    }        
}
