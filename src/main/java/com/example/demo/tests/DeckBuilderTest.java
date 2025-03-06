package com.example.demo.tests;

public class DeckBuilderTest {
	
	/*

    @Mock
    private CardRepository cardRepository;

    @Mock
    private DeckBuilderRepository deckBuilderRepository;

    @InjectMocks
    private DeckBuilderService deckBuilderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialise les mocks
    }

    @Test
    public void testLikeCard() {
        // 1. Préparation des données de test
        Long cardId = 1L;
        DeckCreator user = new DeckCreator();
        user.setEmail("user@example.com");

        Card card = new Card();
        card.setId(cardId);
        card.setLikeNumber(0L);

        // 2. Simulation des comportements des repositories
        when(cardRepository.findById(cardId)).thenReturn(Optional.of(card));
        when(deckBuilderRepository.save(user)).thenReturn(user);
        when(cardRepository.save(card)).thenReturn(card);

        // 3. Exécution de la méthode à tester
        deckBuilderService.likeCard(user, cardId);

        // 4. Vérifications
        // Vérifie que la carte a été ajoutée aux cartes likées par l'utilisateur
        assertTrue(user.getCardsLiked().contains(card));

        // Vérifie que le nombre de likes de la carte a été mis à jour
        assertEquals(1, card.getLikeNumber());

        // Vérifie que les méthodes des repositories ont été appelées
        verify(cardRepository, times(1)).findById(cardId);
        verify(deckBuilderRepository, times(1)).save(user);
        verify(cardRepository, times(1)).save(card);
    }

    @Test
    public void testLikeCard_CardNotFound() {
        // 1. Préparation des données de test
        Long cardId = 1L;
        DeckCreator user = new DeckCreator();
        user.setEmail("user@example.com");

        // 2. Simulation du cas où la carte n'est pas trouvée
        when(cardRepository.findById(cardId)).thenReturn(Optional.empty());

        // 3. Exécution de la méthode à tester
        deckBuilderService.likeCard(user, cardId);

        // 4. Vérifications
        // Vérifie que la carte n'a pas été ajoutée aux cartes likées
        assertFalse(user.getCardsLiked().contains(null));

        // Vérifie que les méthodes de sauvegarde n'ont pas été appelées
        verify(deckBuilderRepository, never()).save(user);
        verify(cardRepository, never()).save(any());
    }

	 */

}
