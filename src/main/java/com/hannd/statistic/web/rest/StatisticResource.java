package com.hannd.statistic.web.rest;

import com.hannd.statistic.domain.Category;
import com.hannd.statistic.service.CategoryService;
import com.hannd.statistic.service.model.CategoryData;
import com.hannd.statistic.web.rest.util.HeaderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * REST controller for managing Category.
 */
@RestController
@RequestMapping("/api")
public class StatisticResource {

    private final Logger log = LoggerFactory.getLogger(StatisticResource.class);

    private static final String ENTITY_NAME = "statistic";

    private final CategoryService categoryService;

    public StatisticResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * POST  /statistic : get statistic data.
     *
     * @return the ResponseEntity with status 201 (Created) and with body the new category, or with status 400 (Bad Request) if the category has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @GetMapping("/statistic")
    public ResponseEntity<?> getStatisticData() throws URISyntaxException {
        log.debug("REST get statistic data");
        RestTemplate restTemplate = new RestTemplate();
        CategoryData[] response = restTemplate.getForObject("https://shopee.vn/api/v1/category_list/", CategoryData[].class);
        List<Category> categoryList = new ArrayList<>();
        Instant timestamp = Instant.now();
        Arrays.asList(response).forEach(c -> {
            // save parent category
            Category category = new Category();
            category.setCatid(Long.valueOf(c.getMain().get("catid")));
            category.setDisplayName(c.getMain().get("display_name"));
            category.setCategoryName(c.getMain().get("name"));
            category.setIsAdult(Integer.valueOf(c.getMain().get("is_adult")));
            category.setSortWeight(Long.valueOf(c.getMain().get("sort_weight")));
            category.setCreateDate(timestamp);
            category.setLastUpdateDate(timestamp);
            categoryList.add(category);

            c.getSub().forEach(sc -> {
                Category subCategory = new Category();
                subCategory.setCatid(Long.valueOf(String.valueOf(sc.get("catid"))));
                if (sc.get("display_name") != null) subCategory.setDisplayName(String.valueOf(sc.get("display_name")));
                if (sc.get("name") != null) subCategory.setCategoryName(String.valueOf(sc.get("name")));
                if (sc.get("is_adult") != null)
                    subCategory.setIsAdult(Integer.valueOf(String.valueOf((sc.get("is_adult")))));
                if (sc.get("sort_weight") != null)
                    subCategory.setSortWeight(Long.valueOf(String.valueOf(sc.get("sort_weight"))));
                subCategory.setParentCategory(category.getCatid());
                subCategory.setCreateDate(timestamp);
                subCategory.setLastUpdateDate(timestamp);
                categoryList.add(subCategory);
            });
        });
        Map<Long, Category> categoryListDB = categoryService.findAllByCatId(categoryList.stream().map(Category::getCatid).collect(Collectors.toList())).stream().collect(Collectors.toMap(Category::getCatid, Function.identity()));
        categoryList.forEach(category -> {
            if (categoryListDB.containsKey(category.getCatid())) {
                category.setId(categoryListDB.get(category.getCatid()).getId());
            }
        });
        categoryService.saveAll(categoryList);
        log.info(response.toString());
//        Category result = categoryService.save(category);
        return ResponseEntity.created(new URI("/api/categories/"))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, ""))
            .body("success");
    }

}
