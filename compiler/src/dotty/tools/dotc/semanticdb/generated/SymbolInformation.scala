// Generated by https://github.com/tanishiking/semanticdb-for-scala3
// Generated by the Scala Plugin for the Protocol Buffer Compiler.
// Do not edit!
//
// Protofile syntax: PROTO3

package dotty.tools.dotc.semanticdb
import dotty.tools.dotc.semanticdb.internal.*

import scala.annotation.internal.sharable

@SerialVersionUID(0L)
final case class SymbolInformation(
    symbol: _root_.scala.Predef.String = "",
    language: dotty.tools.dotc.semanticdb.Language = dotty.tools.dotc.semanticdb.Language.UNKNOWN_LANGUAGE,
    kind: dotty.tools.dotc.semanticdb.SymbolInformation.Kind = dotty.tools.dotc.semanticdb.SymbolInformation.Kind.UNKNOWN_KIND,
    properties: _root_.scala.Int = 0,
    displayName: _root_.scala.Predef.String = "",
    signature: dotty.tools.dotc.semanticdb.Signature = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_signature.toCustom(dotty.tools.dotc.semanticdb.SignatureMessage.defaultInstance),
    annotations: _root_.scala.Seq[dotty.tools.dotc.semanticdb.Annotation] = _root_.scala.Seq.empty,
    access: dotty.tools.dotc.semanticdb.Access = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_access.toCustom(dotty.tools.dotc.semanticdb.AccessMessage.defaultInstance),
    overriddenSymbols: _root_.scala.Seq[_root_.scala.Predef.String] = _root_.scala.Seq.empty,
    documentation: _root_.scala.Option[dotty.tools.dotc.semanticdb.Documentation] = _root_.scala.None
    )  extends SemanticdbGeneratedMessage  derives CanEqual {
    @transient @sharable
    private var __serializedSizeMemoized: _root_.scala.Int = 0
    private def __computeSerializedSize(): _root_.scala.Int = {
      var __size = 0

      {
        val __value = symbol
        if (!__value.isEmpty) {
          __size += SemanticdbOutputStream.computeStringSize(1, __value)
        }
      };

      {
        val __value = language.value
        if (__value != 0) {
          __size += SemanticdbOutputStream.computeEnumSize(16, __value)
        }
      };

      {
        val __value = kind.value
        if (__value != 0) {
          __size += SemanticdbOutputStream.computeEnumSize(3, __value)
        }
      };

      {
        val __value = properties
        if (__value != 0) {
          __size += SemanticdbOutputStream.computeInt32Size(4, __value)
        }
      };

      {
        val __value = displayName
        if (!__value.isEmpty) {
          __size += SemanticdbOutputStream.computeStringSize(5, __value)
        }
      };

      {
        val __value = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_signature.toBase(signature)
        if (__value.serializedSize != 0) {
          __size += 2 + SemanticdbOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
        }
      };
      annotations.foreach { __item =>
        val __value = __item
        __size += 1 + SemanticdbOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      }

      {
        val __value = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_access.toBase(access)
        if (__value.serializedSize != 0) {
          __size += 2 + SemanticdbOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
        }
      };
      overriddenSymbols.foreach { __item =>
        val __value = __item
        __size += SemanticdbOutputStream.computeStringSize(19, __value)
      }
      if (documentation.isDefined) {
        val __value = documentation.get
        __size += 2 + SemanticdbOutputStream.computeUInt32SizeNoTag(__value.serializedSize) + __value.serializedSize
      };
      __size
    }
    override def serializedSize: _root_.scala.Int = {
      var __size = __serializedSizeMemoized
      if (__size == 0) {
        __size = __computeSerializedSize() + 1
        __serializedSizeMemoized = __size
      }
      __size - 1

    }
    def writeTo(`_output__`: SemanticdbOutputStream): _root_.scala.Unit = {
      {
        val __v = symbol
        if (!__v.isEmpty) {
          _output__.writeString(1, __v)
        }
      };
      {
        val __v = kind.value
        if (__v != 0) {
          _output__.writeEnum(3, __v)
        }
      };
      {
        val __v = properties
        if (__v != 0) {
          _output__.writeInt32(4, __v)
        }
      };
      {
        val __v = displayName
        if (!__v.isEmpty) {
          _output__.writeString(5, __v)
        }
      };
      annotations.foreach { __v =>
        val __m = __v
        _output__.writeTag(13, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
      {
        val __v = language.value
        if (__v != 0) {
          _output__.writeEnum(16, __v)
        }
      };
      {
        val __v = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_signature.toBase(signature)
        if (__v.serializedSize != 0) {
          _output__.writeTag(17, 2)
          _output__.writeUInt32NoTag(__v.serializedSize)
          __v.writeTo(_output__)
        }
      };
      {
        val __v = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_access.toBase(access)
        if (__v.serializedSize != 0) {
          _output__.writeTag(18, 2)
          _output__.writeUInt32NoTag(__v.serializedSize)
          __v.writeTo(_output__)
        }
      };
      overriddenSymbols.foreach { __v =>
        val __m = __v
        _output__.writeString(19, __m)
      };
      documentation.foreach { __v =>
        val __m = __v
        _output__.writeTag(20, 2)
        _output__.writeUInt32NoTag(__m.serializedSize)
        __m.writeTo(_output__)
      };
    }
    def withSymbol(__v: _root_.scala.Predef.String): SymbolInformation = copy(symbol = __v)
    def withLanguage(__v: dotty.tools.dotc.semanticdb.Language): SymbolInformation = copy(language = __v)
    def withKind(__v: dotty.tools.dotc.semanticdb.SymbolInformation.Kind): SymbolInformation = copy(kind = __v)
    def withProperties(__v: _root_.scala.Int): SymbolInformation = copy(properties = __v)
    def withDisplayName(__v: _root_.scala.Predef.String): SymbolInformation = copy(displayName = __v)
    def withSignature(__v: dotty.tools.dotc.semanticdb.Signature): SymbolInformation = copy(signature = __v)
    def clearAnnotations = copy(annotations = _root_.scala.Seq.empty)
    def addAnnotations(__vs: dotty.tools.dotc.semanticdb.Annotation *): SymbolInformation = addAllAnnotations(__vs)
    def addAllAnnotations(__vs: Iterable[dotty.tools.dotc.semanticdb.Annotation]): SymbolInformation = copy(annotations = annotations ++ __vs)
    def withAnnotations(__v: _root_.scala.Seq[dotty.tools.dotc.semanticdb.Annotation]): SymbolInformation = copy(annotations = __v)
    def withAccess(__v: dotty.tools.dotc.semanticdb.Access): SymbolInformation = copy(access = __v)
    def clearOverriddenSymbols = copy(overriddenSymbols = _root_.scala.Seq.empty)
    def addOverriddenSymbols(__vs: _root_.scala.Predef.String *): SymbolInformation = addAllOverriddenSymbols(__vs)
    def addAllOverriddenSymbols(__vs: Iterable[_root_.scala.Predef.String]): SymbolInformation = copy(overriddenSymbols = overriddenSymbols ++ __vs)
    def withOverriddenSymbols(__v: _root_.scala.Seq[_root_.scala.Predef.String]): SymbolInformation = copy(overriddenSymbols = __v)
    def getDocumentation: dotty.tools.dotc.semanticdb.Documentation = documentation.getOrElse(dotty.tools.dotc.semanticdb.Documentation.defaultInstance)
    def clearDocumentation: SymbolInformation = copy(documentation = _root_.scala.None)
    def withDocumentation(__v: dotty.tools.dotc.semanticdb.Documentation): SymbolInformation = copy(documentation = Option(__v))




    // @@protoc_insertion_point(GeneratedMessage[dotty.tools.dotc.semanticdb.SymbolInformation])
}

object SymbolInformation  extends SemanticdbGeneratedMessageCompanion[dotty.tools.dotc.semanticdb.SymbolInformation] {
  implicit def messageCompanion: SemanticdbGeneratedMessageCompanion[dotty.tools.dotc.semanticdb.SymbolInformation] = this
  def parseFrom(`_input__`: SemanticdbInputStream): dotty.tools.dotc.semanticdb.SymbolInformation = {
    var __symbol: _root_.scala.Predef.String = ""
    var __language: dotty.tools.dotc.semanticdb.Language = dotty.tools.dotc.semanticdb.Language.UNKNOWN_LANGUAGE
    var __kind: dotty.tools.dotc.semanticdb.SymbolInformation.Kind = dotty.tools.dotc.semanticdb.SymbolInformation.Kind.UNKNOWN_KIND
    var __properties: _root_.scala.Int = 0
    var __displayName: _root_.scala.Predef.String = ""
    var __signature: _root_.scala.Option[dotty.tools.dotc.semanticdb.SignatureMessage] = _root_.scala.None
    val __annotations: _root_.scala.collection.immutable.VectorBuilder[dotty.tools.dotc.semanticdb.Annotation] = new _root_.scala.collection.immutable.VectorBuilder[dotty.tools.dotc.semanticdb.Annotation]
    var __access: _root_.scala.Option[dotty.tools.dotc.semanticdb.AccessMessage] = _root_.scala.None
    val __overriddenSymbols: _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Predef.String] = new _root_.scala.collection.immutable.VectorBuilder[_root_.scala.Predef.String]
    var __documentation: _root_.scala.Option[dotty.tools.dotc.semanticdb.Documentation] = _root_.scala.None
    var _done__ = false
    while (!_done__) {
      val _tag__ = _input__.readTag()
      _tag__ match {
        case 0 => _done__ = true
        case 10 =>
          __symbol = _input__.readStringRequireUtf8()
        case 128 =>
          __language = dotty.tools.dotc.semanticdb.Language.fromValue(_input__.readEnum())
        case 24 =>
          __kind = dotty.tools.dotc.semanticdb.SymbolInformation.Kind.fromValue(_input__.readEnum())
        case 32 =>
          __properties = _input__.readInt32()
        case 42 =>
          __displayName = _input__.readStringRequireUtf8()
        case 138 =>
          __signature = _root_.scala.Some(__signature.fold(LiteParser.readMessage[dotty.tools.dotc.semanticdb.SignatureMessage](_input__))(LiteParser.readMessage(_input__, _)))
        case 106 =>
          __annotations += LiteParser.readMessage[dotty.tools.dotc.semanticdb.Annotation](_input__)
        case 146 =>
          __access = _root_.scala.Some(__access.fold(LiteParser.readMessage[dotty.tools.dotc.semanticdb.AccessMessage](_input__))(LiteParser.readMessage(_input__, _)))
        case 154 =>
          __overriddenSymbols += _input__.readStringRequireUtf8()
        case 162 =>
          __documentation = Option(__documentation.fold(LiteParser.readMessage[dotty.tools.dotc.semanticdb.Documentation](_input__))(LiteParser.readMessage(_input__, _)))
        case tag => _input__.skipField(tag)
      }
    }
    dotty.tools.dotc.semanticdb.SymbolInformation(
        symbol = __symbol,
        language = __language,
        kind = __kind,
        properties = __properties,
        displayName = __displayName,
        signature = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_signature.toCustom(__signature.getOrElse(dotty.tools.dotc.semanticdb.SignatureMessage.defaultInstance)),
        annotations = __annotations.result(),
        access = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_access.toCustom(__access.getOrElse(dotty.tools.dotc.semanticdb.AccessMessage.defaultInstance)),
        overriddenSymbols = __overriddenSymbols.result(),
        documentation = __documentation
    )
  }






  lazy val defaultInstance = dotty.tools.dotc.semanticdb.SymbolInformation(
    symbol = "",
    language = dotty.tools.dotc.semanticdb.Language.UNKNOWN_LANGUAGE,
    kind = dotty.tools.dotc.semanticdb.SymbolInformation.Kind.UNKNOWN_KIND,
    properties = 0,
    displayName = "",
    signature = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_signature.toCustom(dotty.tools.dotc.semanticdb.SignatureMessage.defaultInstance),
    annotations = _root_.scala.Seq.empty,
    access = dotty.tools.dotc.semanticdb.SymbolInformation._typemapper_access.toCustom(dotty.tools.dotc.semanticdb.AccessMessage.defaultInstance),
    overriddenSymbols = _root_.scala.Seq.empty,
    documentation = _root_.scala.None
  )
  sealed abstract class Kind(val value: _root_.scala.Int)  extends SemanticdbGeneratedEnum  derives CanEqual {
    type EnumType = Kind
    def isUnknownKind: _root_.scala.Boolean = false
    def isLocal: _root_.scala.Boolean = false
    def isField: _root_.scala.Boolean = false
    def isMethod: _root_.scala.Boolean = false
    def isConstructor: _root_.scala.Boolean = false
    def isMacro: _root_.scala.Boolean = false
    def isType: _root_.scala.Boolean = false
    def isParameter: _root_.scala.Boolean = false
    def isSelfParameter: _root_.scala.Boolean = false
    def isTypeParameter: _root_.scala.Boolean = false
    def isObject: _root_.scala.Boolean = false
    def isPackage: _root_.scala.Boolean = false
    def isPackageObject: _root_.scala.Boolean = false
    def isClass: _root_.scala.Boolean = false
    def isTrait: _root_.scala.Boolean = false
    def isInterface: _root_.scala.Boolean = false

    final def asRecognized: _root_.scala.Option[dotty.tools.dotc.semanticdb.SymbolInformation.Kind.Recognized] = if (isUnrecognized) _root_.scala.None else _root_.scala.Some(this.asInstanceOf[dotty.tools.dotc.semanticdb.SymbolInformation.Kind.Recognized])
  }

  object Kind  {
    sealed trait Recognized extends Kind


    @SerialVersionUID(0L)
    case object UNKNOWN_KIND extends Kind(0) with Kind.Recognized {
      val index = 0
      val name = "UNKNOWN_KIND"
      override def isUnknownKind: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object LOCAL extends Kind(19) with Kind.Recognized {
      val index = 1
      val name = "LOCAL"
      override def isLocal: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object FIELD extends Kind(20) with Kind.Recognized {
      val index = 2
      val name = "FIELD"
      override def isField: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object METHOD extends Kind(3) with Kind.Recognized {
      val index = 3
      val name = "METHOD"
      override def isMethod: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object CONSTRUCTOR extends Kind(21) with Kind.Recognized {
      val index = 4
      val name = "CONSTRUCTOR"
      override def isConstructor: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object MACRO extends Kind(6) with Kind.Recognized {
      val index = 5
      val name = "MACRO"
      override def isMacro: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object TYPE extends Kind(7) with Kind.Recognized {
      val index = 6
      val name = "TYPE"
      override def isType: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object PARAMETER extends Kind(8) with Kind.Recognized {
      val index = 7
      val name = "PARAMETER"
      override def isParameter: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object SELF_PARAMETER extends Kind(17) with Kind.Recognized {
      val index = 8
      val name = "SELF_PARAMETER"
      override def isSelfParameter: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object TYPE_PARAMETER extends Kind(9) with Kind.Recognized {
      val index = 9
      val name = "TYPE_PARAMETER"
      override def isTypeParameter: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object OBJECT extends Kind(10) with Kind.Recognized {
      val index = 10
      val name = "OBJECT"
      override def isObject: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object PACKAGE extends Kind(11) with Kind.Recognized {
      val index = 11
      val name = "PACKAGE"
      override def isPackage: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object PACKAGE_OBJECT extends Kind(12) with Kind.Recognized {
      val index = 12
      val name = "PACKAGE_OBJECT"
      override def isPackageObject: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object CLASS extends Kind(13) with Kind.Recognized {
      val index = 13
      val name = "CLASS"
      override def isClass: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object TRAIT extends Kind(14) with Kind.Recognized {
      val index = 14
      val name = "TRAIT"
      override def isTrait: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object INTERFACE extends Kind(18) with Kind.Recognized {
      val index = 15
      val name = "INTERFACE"
      override def isInterface: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    final case class Unrecognized(unrecognizedValue: _root_.scala.Int)  extends Kind(unrecognizedValue) with SemanticdbUnrecognizedEnum
    lazy val values = scala.collection.immutable.Seq(UNKNOWN_KIND, LOCAL, FIELD, METHOD, CONSTRUCTOR, MACRO, TYPE, PARAMETER, SELF_PARAMETER, TYPE_PARAMETER, OBJECT, PACKAGE, PACKAGE_OBJECT, CLASS, TRAIT, INTERFACE)
    def fromValue(__value: _root_.scala.Int): Kind = __value match {
      case 0 => UNKNOWN_KIND
      case 3 => METHOD
      case 6 => MACRO
      case 7 => TYPE
      case 8 => PARAMETER
      case 9 => TYPE_PARAMETER
      case 10 => OBJECT
      case 11 => PACKAGE
      case 12 => PACKAGE_OBJECT
      case 13 => CLASS
      case 14 => TRAIT
      case 17 => SELF_PARAMETER
      case 18 => INTERFACE
      case 19 => LOCAL
      case 20 => FIELD
      case 21 => CONSTRUCTOR
      case __other => Unrecognized(__other)
    }


  }
  sealed abstract class Property(val value: _root_.scala.Int)  extends SemanticdbGeneratedEnum  derives CanEqual {
    type EnumType = Property
    def isUnknownProperty: _root_.scala.Boolean = false
    def isAbstract: _root_.scala.Boolean = false
    def isFinal: _root_.scala.Boolean = false
    def isSealed: _root_.scala.Boolean = false
    def isImplicit: _root_.scala.Boolean = false
    def isLazy: _root_.scala.Boolean = false
    def isCase: _root_.scala.Boolean = false
    def isCovariant: _root_.scala.Boolean = false
    def isContravariant: _root_.scala.Boolean = false
    def isVal: _root_.scala.Boolean = false
    def isVar: _root_.scala.Boolean = false
    def isStatic: _root_.scala.Boolean = false
    def isPrimary: _root_.scala.Boolean = false
    def isEnum: _root_.scala.Boolean = false
    def isDefault: _root_.scala.Boolean = false
    def isGiven: _root_.scala.Boolean = false
    def isInline: _root_.scala.Boolean = false
    def isOpen: _root_.scala.Boolean = false
    def isTransparent: _root_.scala.Boolean = false
    def isInfix: _root_.scala.Boolean = false
    def isOpaque: _root_.scala.Boolean = false

    final def asRecognized: _root_.scala.Option[dotty.tools.dotc.semanticdb.SymbolInformation.Property.Recognized] = if (isUnrecognized) _root_.scala.None else _root_.scala.Some(this.asInstanceOf[dotty.tools.dotc.semanticdb.SymbolInformation.Property.Recognized])
  }

  object Property  {
    sealed trait Recognized extends Property


    @SerialVersionUID(0L)
    case object UNKNOWN_PROPERTY extends Property(0) with Property.Recognized {
      val index = 0
      val name = "UNKNOWN_PROPERTY"
      override def isUnknownProperty: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object ABSTRACT extends Property(4) with Property.Recognized {
      val index = 1
      val name = "ABSTRACT"
      override def isAbstract: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object FINAL extends Property(8) with Property.Recognized {
      val index = 2
      val name = "FINAL"
      override def isFinal: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object SEALED extends Property(16) with Property.Recognized {
      val index = 3
      val name = "SEALED"
      override def isSealed: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object IMPLICIT extends Property(32) with Property.Recognized {
      val index = 4
      val name = "IMPLICIT"
      override def isImplicit: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object LAZY extends Property(64) with Property.Recognized {
      val index = 5
      val name = "LAZY"
      override def isLazy: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object CASE extends Property(128) with Property.Recognized {
      val index = 6
      val name = "CASE"
      override def isCase: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object COVARIANT extends Property(256) with Property.Recognized {
      val index = 7
      val name = "COVARIANT"
      override def isCovariant: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object CONTRAVARIANT extends Property(512) with Property.Recognized {
      val index = 8
      val name = "CONTRAVARIANT"
      override def isContravariant: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object VAL extends Property(1024) with Property.Recognized {
      val index = 9
      val name = "VAL"
      override def isVal: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object VAR extends Property(2048) with Property.Recognized {
      val index = 10
      val name = "VAR"
      override def isVar: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object STATIC extends Property(4096) with Property.Recognized {
      val index = 11
      val name = "STATIC"
      override def isStatic: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object PRIMARY extends Property(8192) with Property.Recognized {
      val index = 12
      val name = "PRIMARY"
      override def isPrimary: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object ENUM extends Property(16384) with Property.Recognized {
      val index = 13
      val name = "ENUM"
      override def isEnum: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object DEFAULT extends Property(32768) with Property.Recognized {
      val index = 14
      val name = "DEFAULT"
      override def isDefault: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object GIVEN extends Property(65536) with Property.Recognized {
      val index = 15
      val name = "GIVEN"
      override def isGiven: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object INLINE extends Property(131072) with Property.Recognized {
      val index = 16
      val name = "INLINE"
      override def isInline: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object OPEN extends Property(262144) with Property.Recognized {
      val index = 17
      val name = "OPEN"
      override def isOpen: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object TRANSPARENT extends Property(524288) with Property.Recognized {
      val index = 18
      val name = "TRANSPARENT"
      override def isTransparent: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object INFIX extends Property(1048576) with Property.Recognized {
      val index = 19
      val name = "INFIX"
      override def isInfix: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    case object OPAQUE extends Property(2097152) with Property.Recognized {
      val index = 20
      val name = "OPAQUE"
      override def isOpaque: _root_.scala.Boolean = true
    }

    @SerialVersionUID(0L)
    final case class Unrecognized(unrecognizedValue: _root_.scala.Int)  extends Property(unrecognizedValue) with SemanticdbUnrecognizedEnum
    lazy val values = scala.collection.immutable.Seq(UNKNOWN_PROPERTY, ABSTRACT, FINAL, SEALED, IMPLICIT, LAZY, CASE, COVARIANT, CONTRAVARIANT, VAL, VAR, STATIC, PRIMARY, ENUM, DEFAULT, GIVEN, INLINE, OPEN, TRANSPARENT, INFIX, OPAQUE)
    def fromValue(__value: _root_.scala.Int): Property = __value match {
      case 0 => UNKNOWN_PROPERTY
      case 4 => ABSTRACT
      case 8 => FINAL
      case 16 => SEALED
      case 32 => IMPLICIT
      case 64 => LAZY
      case 128 => CASE
      case 256 => COVARIANT
      case 512 => CONTRAVARIANT
      case 1024 => VAL
      case 2048 => VAR
      case 4096 => STATIC
      case 8192 => PRIMARY
      case 16384 => ENUM
      case 32768 => DEFAULT
      case 65536 => GIVEN
      case 131072 => INLINE
      case 262144 => OPEN
      case 524288 => TRANSPARENT
      case 1048576 => INFIX
      case 2097152 => OPAQUE
      case __other => Unrecognized(__other)
    }


  }
  final val SYMBOL_FIELD_NUMBER = 1
  final val LANGUAGE_FIELD_NUMBER = 16
  final val KIND_FIELD_NUMBER = 3
  final val PROPERTIES_FIELD_NUMBER = 4
  final val DISPLAY_NAME_FIELD_NUMBER = 5
  final val SIGNATURE_FIELD_NUMBER = 17
  final val ANNOTATIONS_FIELD_NUMBER = 13
  final val ACCESS_FIELD_NUMBER = 18
  final val OVERRIDDEN_SYMBOLS_FIELD_NUMBER = 19
  final val DOCUMENTATION_FIELD_NUMBER = 20
  @transient @sharable
  private[semanticdb] val _typemapper_signature: SemanticdbTypeMapper[dotty.tools.dotc.semanticdb.SignatureMessage, dotty.tools.dotc.semanticdb.Signature] = implicitly[SemanticdbTypeMapper[dotty.tools.dotc.semanticdb.SignatureMessage, dotty.tools.dotc.semanticdb.Signature]]
  @transient @sharable
  private[semanticdb] val _typemapper_access: SemanticdbTypeMapper[dotty.tools.dotc.semanticdb.AccessMessage, dotty.tools.dotc.semanticdb.Access] = implicitly[SemanticdbTypeMapper[dotty.tools.dotc.semanticdb.AccessMessage, dotty.tools.dotc.semanticdb.Access]]
  def of(
    symbol: _root_.scala.Predef.String,
    language: dotty.tools.dotc.semanticdb.Language,
    kind: dotty.tools.dotc.semanticdb.SymbolInformation.Kind,
    properties: _root_.scala.Int,
    displayName: _root_.scala.Predef.String,
    signature: dotty.tools.dotc.semanticdb.Signature,
    annotations: _root_.scala.Seq[dotty.tools.dotc.semanticdb.Annotation],
    access: dotty.tools.dotc.semanticdb.Access,
    overriddenSymbols: _root_.scala.Seq[_root_.scala.Predef.String],
    documentation: _root_.scala.Option[dotty.tools.dotc.semanticdb.Documentation]
  ): _root_.dotty.tools.dotc.semanticdb.SymbolInformation = _root_.dotty.tools.dotc.semanticdb.SymbolInformation(
    symbol,
    language,
    kind,
    properties,
    displayName,
    signature,
    annotations,
    access,
    overriddenSymbols,
    documentation
  )
  // @@protoc_insertion_point(GeneratedMessageCompanion[dotty.tools.dotc.semanticdb.SymbolInformation])
}
