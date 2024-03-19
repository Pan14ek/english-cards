package ua.nure.englishcards;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;


public class LayerTests {

    private final String ROOT_PATH = "ua.nure.englishcards";

    @Test
    public void shouldCheckThatApiLayerIsNotUsedInOtherLayers() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(ROOT_PATH);

        ArchRule rule = classes()
                .that().resideInAPackage("..api..")
                .should().onlyBeAccessed().byAnyPackage("..api..")
                .allowEmptyShould(true);

        rule.check(importedClasses);
    }

    @Test
    public void shouldCheckThatServiceLayerCanBeUsedInApiAndServiceLayers() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(ROOT_PATH);

        ArchRule rule = classes()
                .that().resideInAPackage("..service..")
                .should().onlyBeAccessed().byAnyPackage("..api..", "..service..")
                .allowEmptyShould(true);

        rule.check(importedClasses);
    }

    @Test
    public void shouldCheckThatPersistenceLayerCanBeUsedInServiceLayer() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages(ROOT_PATH);

        ArchRule rule = classes()
                .that().resideInAPackage("..persistence..")
                .should().onlyBeAccessed().byAnyPackage("..persistence..", "..service..")
                .allowEmptyShould(true);

        rule.check(importedClasses);
    }

    @Test
    public void shouldNotHaveCycleDependenciesInClasses() {
        slices().matching("ua.nure.englishcards.(*)..").should().beFreeOfCycles();
    }

}
