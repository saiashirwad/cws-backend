package ooad.project.cws.serializable;

import java.io.Serializable;
import java.util.ArrayList;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("StoryLine")
public class StoryLine implements Serializable {

    @Id Long id;

    private Long storyBoardId;
    private ArrayList<SerializableNode> nodes;
}