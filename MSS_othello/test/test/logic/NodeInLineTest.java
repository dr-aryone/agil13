package test.logic;

import game.logic.StandardRuleStrategy;
import game.othello.board.StandardBoard;
import game.othello.board.StandardNode;

import java.lang.reflect.Method;
import java.util.List;

import kth.game.othello.board.Node;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class NodeInLineTest {

	@SuppressWarnings("unchecked")
	private List<Node> callNodesInLineMethod(StandardBoard board, StandardNode node, int change_x, int change_y) {
		try {
			Method nodesInLineMethod = StandardRuleStrategy.class.getDeclaredMethod("nodesInLine", StandardBoard.class,
					StandardNode.class, String.class, int.class, int.class);
			nodesInLineMethod.setAccessible(true);

			StandardRuleStrategy mockedStrategy = Mockito.mock(StandardRuleStrategy.class);

			return (List<Node>) nodesInLineMethod.invoke(mockedStrategy, board, node, node.getOccupantPlayerId(),
					change_x, change_y);
		} catch (Exception e) {
			Assert.fail(e.getMessage());
			return null;
		}
	}

	@Test
	public void hasOneNodeInPositiveX() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(1);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(1);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 1)).thenReturn(mockedOtherNode);

		StandardNode mockedPlayerNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedPlayerNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedPlayerNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(3, 1)).thenReturn(mockedPlayerNode);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 1, 0);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals("otherPlayer", result.get(0).getOccupantPlayerId());
	}

	@Test
	public void hasNoNodeInPositiveX() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(1);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(1);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 1)).thenReturn(mockedOtherNode);

		Mockito.when(mockedBoard.getNode(3, 1)).thenReturn(null);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 1, 0);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void hasOneNodeInNegativeX() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(3);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(1);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 1)).thenReturn(mockedOtherNode);

		StandardNode mockedPlayerNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedPlayerNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedPlayerNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 1)).thenReturn(mockedPlayerNode);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, -1, 0);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals("otherPlayer", result.get(0).getOccupantPlayerId());
	}

	@Test
	public void hasNoNodeInNegativeX() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(3);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(1);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 1)).thenReturn(mockedOtherNode);

		Mockito.when(mockedBoard.getNode(1, 1)).thenReturn(null);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, -1, 0);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void hasOneNodeInPositiveY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(1);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(1);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 2)).thenReturn(mockedOtherNode);

		StandardNode mockedPlayerNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedPlayerNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedPlayerNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 3)).thenReturn(mockedPlayerNode);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 0, 1);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals("otherPlayer", result.get(0).getOccupantPlayerId());
	}

	@Test
	public void hasNoNodeInPositiveY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(1);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(1);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 2)).thenReturn(mockedOtherNode);

		Mockito.when(mockedBoard.getNode(1, 3)).thenReturn(null);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 0, 1);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void hasOneNodeInNegativeY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(1);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(3);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 2)).thenReturn(mockedOtherNode);

		StandardNode mockedPlayerNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedPlayerNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedPlayerNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 1)).thenReturn(mockedPlayerNode);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 0, -1);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals("otherPlayer", result.get(0).getOccupantPlayerId());
	}

	@Test
	public void hasNoNodeInNegativeY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(1);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(3);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 2)).thenReturn(mockedOtherNode);

		Mockito.when(mockedBoard.getNode(1, 1)).thenReturn(null);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 0, -1);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void hasOneNodeInPositiveXPositiveY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(1);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(1);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 2)).thenReturn(mockedOtherNode);

		StandardNode mockedPlayerNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedPlayerNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedPlayerNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(3, 3)).thenReturn(mockedPlayerNode);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 1, 1);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals("otherPlayer", result.get(0).getOccupantPlayerId());
	}

	@Test
	public void hasNoNodeInPositiveXPositiveY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(1);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(1);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 2)).thenReturn(mockedOtherNode);

		Mockito.when(mockedBoard.getNode(3, 3)).thenReturn(null);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 1, 1);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void hasOneNodeInNegativeXNegativeY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(3);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(3);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 2)).thenReturn(mockedOtherNode);

		StandardNode mockedPlayerNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedPlayerNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedPlayerNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 1)).thenReturn(mockedPlayerNode);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, -1, -1);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals("otherPlayer", result.get(0).getOccupantPlayerId());
	}

	@Test
	public void hasNoNodeInNegativeXNegativeY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(3);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(3);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 2)).thenReturn(mockedOtherNode);

		Mockito.when(mockedBoard.getNode(1, 1)).thenReturn(null);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, -1, -1);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void hasOneNodeInPositiveXNegativeY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(3);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(3);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(4, 2)).thenReturn(mockedOtherNode);

		StandardNode mockedPlayerNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedPlayerNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedPlayerNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(5, 1)).thenReturn(mockedPlayerNode);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 1, -1);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals("otherPlayer", result.get(0).getOccupantPlayerId());
	}

	@Test
	public void hasNoNodeInPositiveXNegativeY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(3);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(3);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(4, 2)).thenReturn(mockedOtherNode);

		Mockito.when(mockedBoard.getNode(5, 1)).thenReturn(null);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, 1, -1);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void hasOneNodeInNegativeXPositiveY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(3);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(3);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 4)).thenReturn(mockedOtherNode);

		StandardNode mockedPlayerNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedPlayerNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedPlayerNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(1, 5)).thenReturn(mockedPlayerNode);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, -1, 1);

		Assert.assertEquals(1, result.size());
		Assert.assertEquals("otherPlayer", result.get(0).getOccupantPlayerId());
	}

	@Test
	public void hasNoNodeInNegativeXPositiveY() {
		StandardBoard mockedBoard = Mockito.mock(StandardBoard.class);

		StandardNode mockedSourceNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedSourceNode.getOccupantPlayerId()).thenReturn("thisPlayer");
		Mockito.when(mockedSourceNode.getXCoordinate()).thenReturn(3);
		Mockito.when(mockedSourceNode.getYCoordinate()).thenReturn(3);

		StandardNode mockedOtherNode = Mockito.mock(StandardNode.class);
		Mockito.when(mockedOtherNode.getOccupantPlayerId()).thenReturn("otherPlayer");
		Mockito.when(mockedOtherNode.isMarked()).thenReturn(true);
		Mockito.when(mockedBoard.getNode(2, 4)).thenReturn(mockedOtherNode);

		Mockito.when(mockedBoard.getNode(1, 5)).thenReturn(null);

		List<Node> result = this.callNodesInLineMethod(mockedBoard, mockedSourceNode, -1, 1);

		Assert.assertEquals(0, result.size());
	}

}
