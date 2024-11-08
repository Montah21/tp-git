package tn.esprit.tpfoyer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
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
        universite.setIdUniversite(1L);
        universite.setNomUniversite("Test University");

        // Initialize the list of universites
        listUniversites = new ArrayList<>() {
            {
                Universite university2 = new Universite();
                university2.setIdUniversite(2L);
                university2.setNomUniversite("University 2");
                add(university2);

                Universite university3 = new Universite();
                university3.setIdUniversite(3L);
                university3.setNomUniversite("University 3");
                add(university3);
            }
        };
    }

    @Test
    public void testRetrieveAllUniversites() {
        when(universiteRepository.findAll()).thenReturn(listUniversites);
        List<Universite> retrievedUniversites = universiteService.retrieveAllUniversites();
        Assertions.assertEquals(listUniversites, retrievedUniversites);
        verify(universiteRepository, times(1)).findAll();
    }

    @Test
    public void testRetrieveUniversite() {
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        Universite retrievedUniversite = universiteService.retrieveUniversite(1L);
        Assertions.assertEquals(universite, retrievedUniversite);
        verify(universiteRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddUniversite() {
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);
        Universite addedUniversite = universiteService.addUniversite(universite);
        Assertions.assertNotNull(addedUniversite);
        Assertions.assertEquals("Test University", addedUniversite.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testModifyUniversite() {
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);
        Universite modifiedUniversite = universiteService.modifyUniversite(universite);
        Assertions.assertNotNull(modifiedUniversite);
        Assertions.assertEquals("Test University", modifiedUniversite.getNomUniversite());
        verify(universiteRepository, times(1)).save(universite);
    }

    @Test
    public void testRemoveUniversite() {
        doNothing().when(universiteRepository).deleteById(1L);
        universiteService.removeUniversite(1L);
        verify(universiteRepository, times(1)).deleteById(1L);
    }
}
