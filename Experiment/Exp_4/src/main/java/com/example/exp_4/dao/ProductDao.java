package com.example.exp_4.dao;

import com.example.exp_4.mapper.ProductMapper;
import com.example.exp_4.model.Goods;
import com.example.exp_4.model.GoodsPo;
import com.example.exp_4.model.Product;
import com.example.exp_4.model.ProductPo;
import com.example.exp_4.util.ReturnObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    @Autowired
    private ProductMapper productMapper;

    public ReturnObject<List<Goods>> findGoods(GoodsPo goodsPo) {
        List<GoodsPo> goodsPoList = productMapper.findGoods(goodsPo);
        List<Goods> retGoods = new ArrayList<>(goodsPoList.size());

        for(GoodsPo g : goodsPoList) {
            Goods goods = new Goods(g);
            //System.out.println(goods);
            retGoods.add(goods);
        }

        return new ReturnObject<>(retGoods);
    }

    public ReturnObject<List<Product>> findProduct(ProductPo productPo) {
        List<ProductPo> productPoList = productMapper.findProduct(productPo);
        List<Product> retProducts = new ArrayList<>(productPoList.size());

        for(ProductPo p : productPoList) {
            Product product = new Product(p);
            List<GoodsPo> goodsPoList = p.getGoodsPoList();
            List<Goods> goodsList = new ArrayList<>(goodsPoList.size());
            for(GoodsPo g : goodsPoList) {
                Goods goods = new Goods(g);
                goodsList.add(goods);
            }
            product.setGoodsList(goodsList);
            retProducts.add(product);
        }

        return new ReturnObject<>(retProducts);
    }
}
