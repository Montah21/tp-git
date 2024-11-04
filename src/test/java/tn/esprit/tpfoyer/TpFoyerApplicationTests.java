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
        universite.setId(1L);
        universite.setNom("Test University");

        // Initialize the list of universites
        listUniversites = new ArrayList<>() {
            {
                add(new Universite(2L, "University 2"));
                add(new Universite(3L, "University 3"));
            }
        };
    }

    @Test
    public void testUniversiteServiceOperations() {
        // Mocking behavior for findAll
        when(universiteRepository.findAll()).thenReturn(listUniversites);
        List<Universite> retrievedUniversites = universiteService.retrieveAllUniversites();
        Assertions.assertEquals(listUniversites, retrievedUniversites);
        verify(universiteRepository, times(1)).findAll();

        // Mocking behavior for findById
        when(universiteRepository.findById(1L)).thenReturn(Optional.of(universite));
        Universite retrievedUniversite = universiteService.retrieveUniversite(1L);
        Assertions.assertEquals(universite, retrievedUniversite);
        verify(universiteRepository, times(1)).findById(1L);

        // Mocking behavior for save (add)
        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);
        Universite addedUniversite = universiteService.addUniversite(universite);
        Assertions.assertNotNull(addedUniversite);
        verify(universiteRepository, times(1)).save(universite);

        // Mocking behavior for modify (save)
        Universite modifiedUniversite = universiteService.modifyUniversite(universite);
        Assertions.assertNotNull(modifiedUniversite);
        verify(universiteRepository, times(1)).save(universite);

        // Mocking behavior for delete
        doNothing().when(universiteRepository).deleteById(1L);
        universiteService.removeUniversite(1L);
        verify(universiteRepository, times(1)).deleteById(1L);
    }
}
