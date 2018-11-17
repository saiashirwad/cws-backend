package ooad.project.cws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ooad.project.cws.model.Node;
import ooad.project.cws.repository.NodeRepository;
import ooad.project.cws.repository.StoryLineRepository;

import ooad.project.cws.serializable.StoryLine;

@Service
public class StoryLineService {

    @Autowired NodeService nodeService;
    @Autowired StoryLineRepository storyLineRepository;
    @Autowired NodeRepository nodeRepository;

    public StoryLine getStoryLine(Node node) {

        StoryLine storyLine = new StoryLine();

        storyLine.setId(node.getId());
        storyLine.addNode(node);

        Long parentId = node.getParentId();
        while(parentId != -1) {
            Optional<Node> optNode = nodeRepository.findById(parentId);
            if (optNode.isPresent()) {
                storyLine.addNode(optNode.get());
                parentId = optNode.get().getParentId();
            }
            else {
                return null;
            }
        }
        storyLineRepository.save(storyLine);
        return storyLine;
    }
}