package tn.esprit.tpfoyer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Universite;
import tn.esprit.tpfoyer.repository.UniversiteRepository;
import tn.esprit.tpfoyer.service.UniversiteServiceImpl;

@ExtendWith(MockitoExtension.class)
class UniversiteServiceImpTest {

    @Mock
    private UniversiteRepository universiteRepository; // Mock the repository

    @InjectMocks
    private UniversiteServiceImpl universiteService; // Service under test

    private Universite universite;
    private List<Universite> listUniversites;

    @BeforeEach
    void setUp() {
        // Initialize the object to be tested
        universite = new Universite();
        universite.setId(1L); // Assuming you have a setId method
        universite.setNom("Test University"); // Set other properties as needed

        // Initialize the list of universites
        listUniversites = new ArrayList<>() {
            {
                add(new Universite(2L, "University 2")); // Assuming you have a constructor
                add(new Universite(3L, "University 3"));
            }
        };
    }

    @Test
    public void testRetrieveAllUniversites() {
        Mockito.when(universiteRepository.findAll()).thenReturn(listUniversites); // Mock findAll behavior

        List<Universite> result = universiteService.retrieveAllUniversites(); // Call the method
        Assertions.assertEquals(listUniversites, result); // Assert the result
        Mockito.verify(universiteRepository, Mockito.times(1)).findAll(); // Verify the interaction
    }

    @Test
    public void testRetrieveUniversite() {
        Mockito.when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite)); // Mock findById behavior

        Universite result = universiteService.retrieveUniversite(1L); // Call the method
        Assertions.assertEquals(universite, result); // Assert the entity
        Mockito.verify(universiteRepository, Mockito.times(1)).findById(1L); // Verify the interaction
    }

    @Test
    public void testAddUniversite() {
        Mockito.when(universiteRepository.save(ArgumentMatchers.any(Universite.class))).thenReturn(universite); // Mock save behavior

        Universite result = universiteService.addUniversite(universite); // Call the method
        Assertions.assertNotNull(result); // Assert the result is not null
        Mockito.verify(universiteRepository, Mockito.times(1)).save(universite); // Verify the interaction
    }

    @Test
    public void testModifyUniversite() {
        Mockito.when(universiteRepository.save(ArgumentMatchers.any(Universite.class))).thenReturn(universite); // Mock save behavior

        Universite result = universiteService.modifyUniversite(universite); // Call the method
        Assertions.assertNotNull(result); // Assert the result is not null
        Mockito.verify(universiteRepository, Mockito.times(1)).save(universite); // Verify the interaction
    }

    @Test
    public void testRemoveUniversite() {
        Mockito.doNothing().when(universiteRepository).deleteById(1L); // Mock delete behavior

        universiteService.removeUniversite(1L); // Call the method
        Mockito.verify(universiteRepository, Mockito.times(1)).deleteById(1L); // Verify the interaction
    }
}
