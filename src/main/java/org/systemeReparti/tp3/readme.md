c) Analyse des résultats obtenus
Taille de pool optimale :
En augmentant Pool_size jusqu'au nombre de cœurs CPU, le temps d'exécution diminue, car plus de tâches sont traitées simultanément.
Effet de saturation : 
Au-delà de la capacité CPU (nombre de cœurs), l'augmentation de Pool_size entraîne peu d'amélioration et peut même ralentir en raison de la surcharge liée au changement de contexte.

Optimisation CPU pour tâches intensives : 
Ce test montre qu'une taille de pool de threads ajustée au nombre de cœurs optimise les performances pour des tâches CPU-intensives.

MEILLEURE C'EST FIXIED TREAD POOL par rapport cached pool, 
