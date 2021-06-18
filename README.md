# com.spideo.test.video-recommendation-api

**Video Recommendation API (test for Spideo)**

`Test steps and requirements in French :`

Vous allez devoir développer un mini système de recommandations de vidéo.

Consignes :
On vous demande de nous faire une API REST avec quelques endpoints à implémenter. Le code doit être en java 8 ou
supérieur.

Attendus :

- pour chaques étapes faire un test qui démontre que votre réponse est valide
- fournir une mini documentation ou un petit README
- gardez à l'esprit que le code doit s’inscrire dans une perspective de production et donc il faut nous démontrer votre
  capacité à produire un code de qualité

Étape 1 :
Fournir un endpoint capable d’ajouter une vidéo. Une vidéo à 3 champs un id, un titre et un champ labels exemple :

```json
{
  "id": "97e343ac-3141-45d1-aff6-68a7465d55ec",
  "title": "matrix",
  "labels": [
    "sci-fi",
    "dystopia"
  ]
}
```

Étape 2 :
Faire un endpoint qui retourne une vidéo via son id

Étape 3 :
Faire un endpoint qui retourne une ou plusieurs vidéos via leurs titres.

- tout mot d’au moins 3 lettres pourra retourner un ou plusieurs résultats
- par exemple : “ind” donnera “indiana jones” ou “les indestructibles”

Étape 4 (optionnelle):

- Faire un endpoint qui permet de supprimer une vidéo
- Faire un endpoint qui permet de lister les ids de vidéos qui ont étés supprimées

Étape 5 :

- On veut un moyen de distinguer les types films, des séries. Les règles pour identifier les types sont les suivantes :
    1. Les films ont en plus un champ réalisateur et une date de sortie
    2. Les séries ont un champ nombre d’épisodes
    3. On veut que les champs soient exclusifs à ces types exemple de film :

```json
{
  "id": "86be99d4-ba36-11eb-8529-0242ac130003",
  "title": "Indiana Jones : Raiders of the Lost Ark",
  "director": "Steven Spielberg",
  "release_date": "1982-03-18T12:00:00Z",
  "labels": [
    "adventure",
    "whip",
    "archeology"
  ]
}
```

exemple de série :

```json
{
  "id": "4544e617-84ab-4934-9bda-4d14c7ebcc19",
  "title": "Breaking Bad",
  "number_of_episodes": 62,
  "labels": [
    "chemistry",
    "drug",
    "desert",
    "cancer"
  ]
}
```

- Faire un endpoint pour récupérer les films
- Faire un endpoint pour récupérer les séries
- Faire un test avec type qui ne suit pas les règles précédemment cités et gérer les entrées ne répondant pas au format

Étape 7 :

- Faire un endpoint qui permet à partir d’une vidéo et ses labels d'identifier une ou plusieurs autres vidéos qui sont
  similaires à cette vidéo. L’endpoint prend en paramètre un nombre qui correspond au nombre minimum de labels que l’on
  veut en commun.