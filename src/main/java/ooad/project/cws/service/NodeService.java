package ooad.project.cws.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ooad.project.cws.model.Node;
import ooad.project.cws.repository.NodeRepository;

@Service
public class NodeService {

    @Autowired
    private NodeRepository nodeRepository;

    // public Node traverse(Long id) {
    //     Optional<Node> node = nodeRepository.findById(id);
    //     if (node.isPresent()) {
    //         if (node.get().getParentId() == -1)
    //     }

    // }




}
