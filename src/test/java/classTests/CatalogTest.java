package classTests;

import assemAssist.Catalog;

import static org.junit.jupiter.api.Assertions.*;

class CatalogTest {

    @org.junit.jupiter.api.Test
    void getAvailableModels() throws assemAssist.exceptions.IllegalModelException {
        Catalog a = new Catalog();
        System.out.println(a.getModelSpecifications("A"));
    }

    @org.junit.jupiter.api.Test
    void getAvailableModelNames() {
    }

    @org.junit.jupiter.api.Test
    void addModel() {
    }

    @org.junit.jupiter.api.Test
    void getModelSpecifications() {
    }
}