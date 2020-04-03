package com.jeremy.ardent.world.entity.components;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.badlogic.ashley.core.Component;

class ClientEntityComponent implements Component {

	@Override
	public String toString() {
		boolean first = true;
		StringBuilder builder = new StringBuilder();
		for (Field field : this.getClass().getDeclaredFields()) {
			if (Modifier.isStatic(field.getModifiers())) continue;
			if (!first) builder.append(", ");
			builder.append(field.getName());
			builder.append('=');
			try {
				builder.append(String.valueOf(field.get(this)));
			} catch (Exception exception) {
				builder.append(exception.getClass().getSimpleName());
			}
			first = false;
		}
		return builder.toString();
	}

}
