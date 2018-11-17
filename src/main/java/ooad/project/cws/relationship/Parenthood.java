package ooad.project.cws.relationship;

import org.neo4j.ogm.annotation.EndNode;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.RelationshipEntity;
import org.neo4j.ogm.annotation.StartNode;

import ooad.project.cws.model.Node;

@RelationshipEntity(type="CHILD_OF")
public class Parenthood {

    @Id
    @GeneratedValue
    private Long id;

    @StartNode private Node parent;
    @EndNode private Node child;

	public Node getParent()
	{
		return this.parent;
	}

	public void setParent(Node parent)
	{
		this.parent = parent;
	}

	public Node getChild()
	{
		return this.child;
	}

	public void setChild(Node child)
	{
		this.child = child;
	}


    public Parenthood(Node parent, Node child) {
        this.parent = parent;
        this.child = child;
	}
	
	public Parenthood() {

	}


}