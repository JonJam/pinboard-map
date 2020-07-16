package com.jonjam.pinboard.common.mapstruct.spi;

import java.util.regex.Pattern;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.PackageElement;
import javax.lang.model.element.TypeElement;

import org.mapstruct.ap.spi.BuilderInfo;
import org.mapstruct.ap.spi.DefaultBuilderProvider;
import org.mapstruct.ap.spi.TypeHierarchyErroneousException;

/**
 * Copied from org.mapstruct.ap.spi with the following changes:
 * - Using custom Immutables annotation.
 * - Removing appending of Immutables to name as we hide them.
 */
public class CustomImmutablesBuilderProvider extends DefaultBuilderProvider {

    private static final Pattern JAVA_JAVAX_PACKAGE = Pattern.compile( "^javax?\\..*" );

    // Changed value from: org.immutables.value.Value.Immutable
    private static final String IMMUTABLE_FQN = "com.jonjam.pinboard.common.objectmodel.Immutable";

    @Override
    protected BuilderInfo findBuilderInfo(TypeElement typeElement) {
        Name name = typeElement.getQualifiedName();
        if ( name.length() == 0 || JAVA_JAVAX_PACKAGE.matcher( name ).matches() ) {
            return null;
        }
        TypeElement immutableAnnotation = elementUtils.getTypeElement( IMMUTABLE_FQN );
        if ( immutableAnnotation != null ) {
            BuilderInfo info = findBuilderInfoForImmutables(
                typeElement,
                immutableAnnotation
            );
            if ( info != null ) {
                return info;
            }
        }

        return super.findBuilderInfo( typeElement );
    }

    protected BuilderInfo findBuilderInfoForImmutables(TypeElement typeElement,
        TypeElement immutableAnnotation) {
        for ( AnnotationMirror annotationMirror : elementUtils.getAllAnnotationMirrors( typeElement ) ) {
            if ( typeUtils.isSameType( annotationMirror.getAnnotationType(), immutableAnnotation.asType() ) ) {
                TypeElement immutableElement = asImmutableElement( typeElement );
                if ( immutableElement != null ) {
                    return super.findBuilderInfo( immutableElement );
                }
                else {
                    // Immutables processor has not run yet. Trigger a postpone to the next round for MapStruct
                    throw new TypeHierarchyErroneousException( typeElement );
                }
            }
        }
        return null;
    }

    protected TypeElement asImmutableElement(TypeElement typeElement) {
        Element enclosingElement = typeElement.getEnclosingElement();
        StringBuilder builderQualifiedName = new StringBuilder( typeElement.getQualifiedName().length() + 17 );
        if ( enclosingElement.getKind() == ElementKind.PACKAGE ) {
            builderQualifiedName.append( ( (PackageElement) enclosingElement ).getQualifiedName().toString() );
        }
        else {
            builderQualifiedName.append( ( (TypeElement) enclosingElement ).getQualifiedName().toString() );
        }

        if ( builderQualifiedName.length() > 0 ) {
            builderQualifiedName.append( "." );
        }

        // Removed appending "Immutable" as we are hiding the generated class in our style.
        // builderQualifiedName.append( "Immutable" ).append( typeElement.getSimpleName() );
        builderQualifiedName.append(typeElement.getSimpleName());

        return elementUtils.getTypeElement( builderQualifiedName );
    }
}
