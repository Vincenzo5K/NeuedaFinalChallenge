package com.CreditCardManagement.CreditCardManagement.repo;

import com.CreditCardManagement.CreditCardManagement.dto.CategorySpend;
import com.CreditCardManagement.CreditCardManagement.dto.StateSales;
import com.CreditCardManagement.CreditCardManagement.dto.LowHigh;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class TransactionDALMongoTemplate {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<StateSales> getTransactionTotalsByState() {
        GroupOperation groupByStateSumSales = group("state").sum("amt")
                .as("total_sales");

        MatchOperation allStates = match(new Criteria("state").exists(true));

        ProjectionOperation includes = project("total_sales").and("state").previousOperation();

        SortOperation sortBySalesDesc = sort(Sort.by(Sort.Direction.DESC,"total_sales"));

        Aggregation aggregation = newAggregation(allStates,groupByStateSumSales,sortBySalesDesc,includes);
        AggregationResults<StateSales> groupResults = mongoTemplate
                .aggregate(aggregation, "Transactions", StateSales.class);
        List<StateSales> result = groupResults.getMappedResults();
        return result;
    }

    public List<CategorySpend> getCategorySpendByState(String state) {
        GroupOperation groupByStateTransactions = group("category").sum("amt")
                .as("total_spend");

        MatchOperation isState = match(new Criteria("state").is(state));

        ProjectionOperation includes = project("total_spend").and("category").previousOperation();

        SortOperation sortBySpendDesc = sort(Sort.by(Sort.Direction.DESC,"total_spend"));

        Aggregation aggregation = newAggregation(isState,groupByStateTransactions,sortBySpendDesc,includes);
        AggregationResults<CategorySpend> groupResults = mongoTemplate
                .aggregate(aggregation, "Transactions", CategorySpend.class);
        List<CategorySpend> result = groupResults.getMappedResults();
        return result;
    }

    public List<LowHigh> getLowHighTransactionTotals(double amount) {
        List<LowHigh> lowHigh = new ArrayList<>();
        GroupOperation groupByHighLow = group().sum("amt").as("transTotal");
        MatchOperation lowAmounts = match(new Criteria("amt").lte(amount));
        Aggregation lowAggregation = newAggregation(lowAmounts, groupByHighLow);
        AggregationResults<LowHigh> groupResults = mongoTemplate.aggregate(lowAggregation, "Transactions", LowHigh.class);
        LowHigh result = groupResults.getUniqueMappedResult();
        LowHigh lh = new LowHigh();
        lh.setLowHigh("Low Amounts");
        lh.setTransTotal(result.getTransTotal());
        lowHigh.add(lh);
        MatchOperation highAmounts = match(new Criteria("amt").gt(amount));
        Aggregation highAggregation = newAggregation(highAmounts, groupByHighLow);
        groupResults = mongoTemplate.aggregate(highAggregation, "Transactions", LowHigh.class);
        result = groupResults.getUniqueMappedResult();
        lh = new LowHigh();
        lh.setLowHigh("High Amounts");
        lh.setTransTotal(result.getTransTotal());
        lowHigh.add(lh);
        return lowHigh;
    }
}
