package com.jeremy.ardent.level;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.util.Arrays;
import java.util.HashSet;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;

public class CollisionMap {

	private final HashSet<Fixture> fixtures;
	private Body body;

	public CollisionMap() {
		fixtures = new HashSet<>();
	}

	public void update(Level level, Tile tile) {
		if (body == null) body = level.getWorld().createBody(new BodyDef());

		Fixture[] changedFixtures = getChangedFixtures(level, tile);
		Fixture newFixture = createFixture(toVertices(tile));
		if (changedFixtures.length != 0) {
			Fixture[] toMerge = new Fixture[changedFixtures.length + 1];
			System.arraycopy(changedFixtures, 0, toMerge, 1, changedFixtures.length);
			toMerge[0] = newFixture;
			mergeFixtures(toMerge);
		}
	}

	private void mergeFixtures(Fixture[] fixtures) {

	}

	public Fixture[] getChangedFixtures(Level level, Tile tile) {
		return fixtures.stream().filter(fixture -> {
			Vector2[] tileVertices = toVertices(tile);
			for (Vector2 tileVertex : tileVertices) {
				if (fixture.testPoint(tileVertex)) {
					return true;
				} else {
					System.out.println("fixture does not contain " + tileVertex);
				}
			}
			return false;
		}).toArray(Fixture[]::new);
	}

	private Fixture createFixture(Vector2[] vertices) {
		System.out.println("Bulding fixture: " + Arrays.asList(vertices));
		PolygonShape shape = new PolygonShape();
		shape.set(vertices);
		Fixture fixture = body.createFixture(shape, 0.0f);
		fixtures.add(fixture);
		shape.dispose();
		return fixture;
	}

	private Vector2[] toVertices(Tile tile) {
		Rectangle bounds = tile.getCollision();
		return new Vector2[] { //
				new Vector2(tile.getX() + bounds.x, tile.getY() + bounds.y), //
				new Vector2(tile.getX() + bounds.x + bounds.width, tile.getY() + bounds.y), //
				new Vector2(tile.getX() + bounds.x + bounds.width, tile.getY() + bounds.y + bounds.height), //
				new Vector2(tile.getX() + bounds.x, tile.getY() + bounds.y + bounds.height) //
		};
	}

	private Vector2[] toVertices(PolygonShape shape) {
		Vector2[] vertices = new Vector2[shape.getVertexCount()];
		for (int i = 0; i < vertices.length; i++) shape.getVertex(i, vertices[i] = new Vector2());
		return vertices;
	}

	private static Vector2 intersectionPoint(Vector2 point1A, Vector2 point2A, Vector2 point1B, Vector2 point2B) {
		float a1 = point2A.y - point1A.y;
		float b1 = point1A.x - point2A.x;
		float c1 = a1 * point1A.x + b1 * point1A.y;
		float a2 = point2B.y - point1B.y;
		float b2 = point1B.x - point2B.x;
		float c2 = a2 * point1B.x + b2 * point1B.y;
		float delta = a1 * b2 - a2 * b1;
		float x = (b2 * c1 - b1 * c2) / delta;
		float y = (a1 * c2 - a2 * c1) / delta;
		if (x < max(min(point1A.x, point2A.x), min(point1B.x, point2B.x))) return null;
		if (x > min(max(point1A.x, point2A.x), max(point1B.x, point2B.x))) return null;
		if (y < max(min(point1A.y, point2A.y), min(point1B.y, point2B.y))) return null;
		if (y > min(max(point1A.y, point2A.y), max(point1B.y, point2B.y))) return null;
		return new Vector2(x, y);
	}

}
