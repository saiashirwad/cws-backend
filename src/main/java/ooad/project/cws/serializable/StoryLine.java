package ooad.project.cws.serializable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import ooad.project.cws.model.Node;

@RedisHash("StoryLine")
public class StoryLine implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    Long id;


	public Long getId()
	{
		return this.id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

    private Long storyBoardId;
    private LinkedHashSet<Node> nodes;

	public Long getStoryboardid()
	{
		return this.storyBoardId;
	}

	public void setStoryboardid(Long storyBoardId)
	{
		this.storyBoardId = storyBoardId;
	}

	public LinkedHashSet<Node> getNodes()
	{
		return this.nodes;
	}

	public void setNodes(LinkedHashSet<Node> nodes)
	{
		this.nodes = nodes;
    }
    
    public void addNode(Node node) {
        if (this.nodes == null) {
            this.nodes = new LinkedHashSet<>();
        }
        this.nodes.add(node);
    }

    public StoryLine() {

    }


}