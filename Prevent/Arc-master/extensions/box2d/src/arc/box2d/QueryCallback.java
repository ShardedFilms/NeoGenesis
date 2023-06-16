package arc.box2d;

/** Callback class for AABB queries. */
public interface QueryCallback{
    /**
     * Called for each fixture found in the query AABB.
     * @return false to terminate the query.
     */
    boolean reportFixture(Fixture fixture);
}
