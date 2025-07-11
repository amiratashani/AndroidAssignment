package ir.miare.androidcodechallenge.domain.usecase


const val FAKE_DATA= """[
  {
    "league": {
      "name": "Serie A",
      "country": "Italy",
      "rank": 3,
      "total_matches": 32
    },
    "players": [
      {
        "name": "Edin Dzeko",
        "total_goal": 17,
        "team": {
          "name": "Inter",
          "rank": 2
        }
      },
      {
        "name": "Angel Di Maria",
        "total_goal": 9,
        "team": {
          "name": "Juventus",
          "rank": 3
        }
      },
      {
        "name": "Zlatan Ibrahimovic",
        "total_goal": 17,
        "team": {
          "name": "Ac Milan",
          "rank": 1
        }
      }
    ]
  },
  {
    "league": {
      "name": "Premier League",
      "country": "England",
      "rank": 1,
      "total_matches": 38
    },
    "players": [
      {
        "name": "Mohammad Salah",
        "total_goal": 25,
        "team": {
          "name": "Liverpool",
          "rank": 2
        }
      },
      {
        "name": "Erling Haaland",
        "total_goal": 33,
        "team": {
          "name": "Man City",
          "rank": 1
        }
      },
      {
        "name": "Marcus Rashford",
        "total_goal": 17,
        "team": {
          "name": "Man United",
          "rank": 3
        }
      }
    ]
  },
  {
    "league": {
      "name": "LaLiga",
      "country": "Spain",
      "rank": 2,
      "total_matches": 36
    },
    "players": [
      {
        "name": "Antoine Griezmann",
        "total_goal": 21,
        "team": {
          "name": "Atletico",
          "rank": 3
        }
      },
      {
        "name": "Karim Benzema",
        "total_goal": 27,
        "team": {
          "name": "Real Madrid",
          "rank": 2
        }
      },
      {
        "name": "Robert Lewandowski",
        "total_goal": 23,
        "team": {
          "name": "Barcelona",
          "rank": 1
        }
      }
    ]
  }
]
"""