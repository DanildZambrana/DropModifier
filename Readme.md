# DropModifier [![wakatime](https://wakatime.com/badge/user/09da6df9-171a-4950-8424-21c28008a13d/project/673d7263-0acd-4d0b-97b6-8c4b0ab2092a.svg)](https://wakatime.com/badge/user/09da6df9-171a-4950-8424-21c28008a13d/project/673d7263-0acd-4d0b-97b6-8c4b0ab2092a)

Remove drop in specific world to entity.

## Installation
 Put the jar in your `plugins` folder.

## Configuration

- `Entity Name: ` https://papermc.io/javadocs/paper/1.17/org/bukkit/entity/EntityType.html
- `Item Material Type: ` https://papermc.io/javadocs/paper/1.17/org/bukkit/Material.html
```yml
# world-name:
#  Entity-name: 
#    - Banned Item 

world:
  SKELETON:
    - BOW

world_nether:
  PIGLIN:
    - GOLDEN_SWORD
```