/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package org.gomiles.rnd.dto;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class ContactDto extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 8526802805630885073L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"ContactDto\",\"namespace\":\"org.gomiles.rnd.dto\",\"fields\":[{\"name\":\"contact\",\"type\":\"string\",\"doc\":\"Contact Number\"},{\"name\":\"type\",\"type\":\"string\",\"doc\":\"Home or Work\"}],\"version\":\"1\"}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<ContactDto> ENCODER =
      new BinaryMessageEncoder<ContactDto>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<ContactDto> DECODER =
      new BinaryMessageDecoder<ContactDto>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<ContactDto> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<ContactDto> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<ContactDto>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this ContactDto to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a ContactDto from a ByteBuffer. */
  public static ContactDto fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  /** Contact Number */
  @Deprecated public java.lang.CharSequence contact;
  /** Home or Work */
  @Deprecated public java.lang.CharSequence type;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public ContactDto() {}

  /**
   * All-args constructor.
   * @param contact Contact Number
   * @param type Home or Work
   */
  public ContactDto(java.lang.CharSequence contact, java.lang.CharSequence type) {
    this.contact = contact;
    this.type = type;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return contact;
    case 1: return type;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: contact = (java.lang.CharSequence)value$; break;
    case 1: type = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'contact' field.
   * @return Contact Number
   */
  public java.lang.CharSequence getContact() {
    return contact;
  }

  /**
   * Sets the value of the 'contact' field.
   * Contact Number
   * @param value the value to set.
   */
  public void setContact(java.lang.CharSequence value) {
    this.contact = value;
  }

  /**
   * Gets the value of the 'type' field.
   * @return Home or Work
   */
  public java.lang.CharSequence getType() {
    return type;
  }

  /**
   * Sets the value of the 'type' field.
   * Home or Work
   * @param value the value to set.
   */
  public void setType(java.lang.CharSequence value) {
    this.type = value;
  }

  /**
   * Creates a new ContactDto RecordBuilder.
   * @return A new ContactDto RecordBuilder
   */
  public static org.gomiles.rnd.dto.ContactDto.Builder newBuilder() {
    return new org.gomiles.rnd.dto.ContactDto.Builder();
  }

  /**
   * Creates a new ContactDto RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new ContactDto RecordBuilder
   */
  public static org.gomiles.rnd.dto.ContactDto.Builder newBuilder(org.gomiles.rnd.dto.ContactDto.Builder other) {
    return new org.gomiles.rnd.dto.ContactDto.Builder(other);
  }

  /**
   * Creates a new ContactDto RecordBuilder by copying an existing ContactDto instance.
   * @param other The existing instance to copy.
   * @return A new ContactDto RecordBuilder
   */
  public static org.gomiles.rnd.dto.ContactDto.Builder newBuilder(org.gomiles.rnd.dto.ContactDto other) {
    return new org.gomiles.rnd.dto.ContactDto.Builder(other);
  }

  /**
   * RecordBuilder for ContactDto instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<ContactDto>
    implements org.apache.avro.data.RecordBuilder<ContactDto> {

    /** Contact Number */
    private java.lang.CharSequence contact;
    /** Home or Work */
    private java.lang.CharSequence type;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(org.gomiles.rnd.dto.ContactDto.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.contact)) {
        this.contact = data().deepCopy(fields()[0].schema(), other.contact);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing ContactDto instance
     * @param other The existing instance to copy.
     */
    private Builder(org.gomiles.rnd.dto.ContactDto other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.contact)) {
        this.contact = data().deepCopy(fields()[0].schema(), other.contact);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.type)) {
        this.type = data().deepCopy(fields()[1].schema(), other.type);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'contact' field.
      * Contact Number
      * @return The value.
      */
    public java.lang.CharSequence getContact() {
      return contact;
    }

    /**
      * Sets the value of the 'contact' field.
      * Contact Number
      * @param value The value of 'contact'.
      * @return This builder.
      */
    public org.gomiles.rnd.dto.ContactDto.Builder setContact(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.contact = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'contact' field has been set.
      * Contact Number
      * @return True if the 'contact' field has been set, false otherwise.
      */
    public boolean hasContact() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'contact' field.
      * Contact Number
      * @return This builder.
      */
    public org.gomiles.rnd.dto.ContactDto.Builder clearContact() {
      contact = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'type' field.
      * Home or Work
      * @return The value.
      */
    public java.lang.CharSequence getType() {
      return type;
    }

    /**
      * Sets the value of the 'type' field.
      * Home or Work
      * @param value The value of 'type'.
      * @return This builder.
      */
    public org.gomiles.rnd.dto.ContactDto.Builder setType(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.type = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * Home or Work
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'type' field.
      * Home or Work
      * @return This builder.
      */
    public org.gomiles.rnd.dto.ContactDto.Builder clearType() {
      type = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ContactDto build() {
      try {
        ContactDto record = new ContactDto();
        record.contact = fieldSetFlags()[0] ? this.contact : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.type = fieldSetFlags()[1] ? this.type : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<ContactDto>
    WRITER$ = (org.apache.avro.io.DatumWriter<ContactDto>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<ContactDto>
    READER$ = (org.apache.avro.io.DatumReader<ContactDto>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
