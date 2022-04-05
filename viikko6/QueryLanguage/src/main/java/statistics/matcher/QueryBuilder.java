/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package statistics.matcher;

/**
 *
 * @author ylireett
 */
public class QueryBuilder {
    private Matcher matcher;
    
    public QueryBuilder() {
        this.matcher = new All();
    }
    
    public Matcher build() {
        Matcher kopsu = this.matcher;
        this.matcher = new All();
        return kopsu;
    }
    
    public QueryBuilder playsIn(String team) {
        this.matcher = new And(this.matcher, new PlaysIn(team));
        return this;
    }
    
    public QueryBuilder hasAtLeast(int value, String what) {
        this.matcher = new And(this.matcher, new HasAtLeast(value, what));
        return this;
    }
    
    public QueryBuilder hasFewerThan(int value, String what) {
        this.matcher = new And(this.matcher, new HasFewerThan(value, what));
        return this;
    }
    
    public QueryBuilder oneOf(Matcher... matchers) {
        this.matcher = new Or(matchers);
        return this;
    }
    
}
