package com.kamilzki.terraristic.bootstrap;

import com.kamilzki.terraristic.domain.Animal;
import com.kamilzki.terraristic.domain.CategoryOfAnimal;
import com.kamilzki.terraristic.domain.TypeOfFood;
import com.kamilzki.terraristic.repositories.AnimalRepository;
import com.kamilzki.terraristic.repositories.CategoryOfAnimalRepository;
import com.kamilzki.terraristic.repositories.TypeOfFoodRepository;
import com.sun.deploy.security.ValidationState;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.*;

@Slf4j
@Component
public class AnimalBootstrap implements ApplicationListener<ContextRefreshedEvent>
{

    private AnimalRepository animalRepository;
    private CategoryOfAnimalRepository categoryRepository;
    private TypeOfFoodRepository foodRepository;

    public AnimalBootstrap(AnimalRepository animalRepository, CategoryOfAnimalRepository categoryRepository, TypeOfFoodRepository foodRepository)
    {
        this.animalRepository = animalRepository;
        this.categoryRepository = categoryRepository;
        this.foodRepository = foodRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent)
    {
        animalRepository.saveAll(getAnimals());
        log.debug("Loading templates.animal bootstrap data.");
    }

    private List<Animal> getAnimals() {

        List<Animal> animals = new ArrayList<>(5);

        //get categories of animals

        Optional<CategoryOfAnimal> reptileCategoryOptional = categoryRepository.findByNameCategory("Reptile");
        if(!reptileCategoryOptional.isPresent())
        {
            throw new RuntimeException("Expected CategoryOfAnimal Not Found");
        }

        Optional<CategoryOfAnimal> snakeCategoryOptional = categoryRepository.findByNameCategory("Snake");
        if(!snakeCategoryOptional.isPresent())
        {
            throw new RuntimeException("Expected CategoryOfAnimal Not Found");
        }

        Optional<CategoryOfAnimal> spiderCategoryOptional = categoryRepository.findByNameCategory("Spider");
        if(!spiderCategoryOptional.isPresent())
        {
            throw new RuntimeException("Expected CategoryOfAnimal Not Found");
        }

        Optional<CategoryOfAnimal> insectCategoryOptional = categoryRepository.findByNameCategory("Insect");
        if(!insectCategoryOptional.isPresent())
        {
            throw new RuntimeException("Expected CategoryOfAnimal Not Found");
        }

        CategoryOfAnimal reptileCategory = reptileCategoryOptional.get();
        CategoryOfAnimal snakeCategory = snakeCategoryOptional.get();
        CategoryOfAnimal spiderCategory = spiderCategoryOptional.get();
        CategoryOfAnimal insectCategory = insectCategoryOptional.get();


        //get TypeOfFood
        Optional<TypeOfFood> insectFoodOptional = foodRepository.findByNameFood("Insect");
        if(!insectFoodOptional.isPresent())
        {
            throw new RuntimeException("Expected CategoryOfAnimal Not Found");
        }

        Optional<TypeOfFood> fruitFoodOptional = foodRepository.findByNameFood("Fruit");
        if(!fruitFoodOptional.isPresent())
        {
            throw new RuntimeException("Expected CategoryOfAnimal Not Found");
        }

        Optional<TypeOfFood> vegetableFoodOptional = foodRepository.findByNameFood("Vegetable");
        if(!vegetableFoodOptional.isPresent())
        {
            throw new RuntimeException("Expected CategoryOfAnimal Not Found");
        }

        Optional<TypeOfFood> vertebrateFoodOptional = foodRepository.findByNameFood("Vertebrate");
        if(!vertebrateFoodOptional.isPresent())
        {
            throw new RuntimeException("Expected CategoryOfAnimal Not Found");
        }

        TypeOfFood insectFood = insectFoodOptional.get();
        TypeOfFood fruitFood = fruitFoodOptional.get();
        TypeOfFood vegetableFood = vegetableFoodOptional.get();
        TypeOfFood vertebrateFood = vertebrateFoodOptional.get();

        Set<TypeOfFood> insectFoodSet = new HashSet<>(1);
        insectFoodSet.add(insectFood);

        Set<TypeOfFood> vertebrateFoodSet = new HashSet<>(1);
        vertebrateFoodSet.add(vertebrateFood);

        Set<TypeOfFood> insectAndFruitSet = new HashSet<>(2);
        insectAndFruitSet.add(insectFood);
        insectAndFruitSet.add(fruitFood);

        Set<TypeOfFood> insectAndVegetableAndFruit = new HashSet<>(3);
        insectAndVegetableAndFruit.add(insectFood);
        insectAndVegetableAndFruit.add(vegetableFood);
        insectAndVegetableAndFruit.add(fruitFood);
        insectAndVegetableAndFruit.add(vertebrateFood);

        Animal brachypelmaSmithi = new Animal("Brachypelma Smithi", "", 20.00, 20, 29, spiderCategory);
        brachypelmaSmithi.setFoods(insectFoodSet);
        animals.add(brachypelmaSmithi);

        Animal pogonaVitticeps = new Animal("Pogona Vitticeps", "Gatunek jaszczurki z rodziny agamowatych, w języku polskim określana nazwą agama brodata. Wokół głowy i grzbiet pokrywają ostre łuski, które tworzą kolczastą brodę, gdy podrażnione zwierzę nadyma podgardle.",
                200.0, 20, 33, insectAndVegetableAndFruit, reptileCategory);
        animals.add(pogonaVitticeps);

        Animal lygodactylusWilliamsi = new Animal("Lygodactylus williamsi", "To niewielkie gekony – osobniki Lygodactylus williamsi mogą osiągać ok. 8 cm długości całkowitej (Bruse i współ., 2005). Trudno jest oprzeć się zachwytom nad kolorystyką osobników tego gatunku, w szczególności dotyczy to samców, które przyjmują intensywne, turkusowe ubarwienie ciała, z ciemnym (czarnym) gardłem i pomarańczową stroną brzuszną. Dodatkowo na głowie samca widoczne są delikatne, czarne pręgi biegnące od pyska ku oczom i za oczami. Samice mniejsze od samców i niestety już tak oszałamiająco piękne nie są – ich ubarwienie to różne odcienie koloru zielono-brązowego. Młode lub zdominowane samce upodobniają się kolorystyką do samic.\n" +
                "\n" +
                "W przypadku zagrożenia czy też innej stresującej sytuacji osobniki tego gatunku mają zdolność odrzucenia ogona – autotomii, ogon ulega regeneracji.",
                180.0, 21, 28, insectAndFruitSet, reptileCategory);
        animals.add(lygodactylusWilliamsi);

        Animal pythonReticulatus = new Animal("Python reticulatus", "Siatkowane pytony mają jeden z największych zasięgów występowania ze wszystkich znanych światu pytonów. Zamieszkują rozległy obszar południowo-wschodniej Azji, na który składa się Półwysep Malajski, Borneo, Jawa, Sumatra, Timor, Ceram oraz Filipiny.\n" +
                "\n" +
                "Wspaniale przystosowały się do życia w środowisku człowieka, osiedlając się na obszarach rolniczych, a nawet w miastach. Są drapieżnikami o szerokich preferencjach pokarmowych zdolne pochłonąć każdy niemalże pokarm, od myszy począwszy, poprzez ptaki, koty, psy aż po ssaki kopytne. Generalnie zjadają wszystko co są w stanie połknąć, zwierzęta zimnokrwiste takie jak płazy i ryby w to włączając.",
                375.0, 26, 32, vertebrateFoodSet, snakeCategory);
        animals.add(pythonReticulatus);


        return animals;
    }
}
