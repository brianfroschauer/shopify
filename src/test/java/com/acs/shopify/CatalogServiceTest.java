package com.acs.shopify;

import com.acs.shopify.model.Catalog;
import com.acs.shopify.model.CatalogItem;
import com.acs.shopify.model.Product;
import com.acs.shopify.repository.CatalogRepository;
import com.acs.shopify.repository.ProductRepository;
import com.acs.shopify.service.CatalogService;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Author: brianfroschauer
 * Date: 2019-05-24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CatalogServiceTest {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CatalogRepository catalogRepository;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void test001_cuandoSeCreaUnCatalogoDebeEstarVacio() {

        Catalog catalog = new Catalog();

        catalog = catalogService.save(catalog);

        assertNotNull(catalog);

        assertEquals(0, catalog.size());
    }

    @Test
    public void cuandoSeAgregaUnProductoAlCatalogoDebeDevolverElCatalogoActualizado() {

        Catalog catalog = new Catalog();

        Product product = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product, 1000F);

        assertNotNull(catalog);

        assertNotNull(catalog.getId());

        assertEquals(1, catalog.size());
    }

    @Test
    public void cuandoSeAgregaElMismoProductoAlCatalogoNoSeDebenRealizarModificaciones() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        assertEquals(1, catalog.size());
    }

    @Test
    public void cuandoSeAgreganDosProductosAlCatalogoElSizeDebeSerDos() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        Product product2 = new Product("iPhone 8 Plus", "Dual camera");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product2, 800F);

        assertEquals(2, catalog.size());
    }

    @Test
    public void cuandoSeAgregaElMismoProductoConUnNuevoPrecioDebeSerActualizado() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product1, 800F);

        assertEquals(1, catalog.size());
    }

    @Test
    public void cuandoSeAgreganDosProductosDiferentesConLasMismasCaracteristicasDebeActualizarElProductoOriginal() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        Product product2 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.addProduct(catalog, product2, 800F);

        assertEquals(1, catalog.size());

        assertEquals(1, productRepository.findAll().size());
    }

    @Test
    public void cuandoSeEliminaUnProductoDelCatalogoDebeDevolverElCatalogoSinElProducto() {

        Catalog catalog = new Catalog();

        Product product1 = new Product("iPhone X", "All screen");

        catalog = catalogService.addProduct(catalog, product1, 1000F);

        catalog = catalogService.removeProduct(catalog, new CatalogItem(product1, 1000F));

        assertEquals(0, catalog.size());

        // con esto me aseguro que a nivel datos tambien este eliminado, ya que lo que
        // puede pasar es que se desacople de la entidad (le setea en null el catalogId al catalogItem)
        // pero realmente no se elimina a nivel datos
        assertEquals(0, productRepository.findAll().size());

    }

    @After
    public void tearDown() {
        catalogRepository.deleteAll();
        productRepository.deleteAll();
    }
}
